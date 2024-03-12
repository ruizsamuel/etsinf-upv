package practica2;

import java.util.concurrent.CountDownLatch;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingUtilities;

public class StateManager {
    DefaultListModel<State> stateList;
    int ended=0;
    int[] fork; //forks state (for each fork, it has an owner or N==free)
    State lastState;
    private boolean deadlock=false;
    CountDownLatch done=new CountDownLatch(1);

    public StateManager(JList<State> states) {
        stateList = (DefaultListModel<State>) states.getModel();
        // Set initial state
        fork= new int[5]; for (int i=0; i<5; i++) fdrop(i); //init. free
        final State ne = new State();
        lastState = ne;
        // Initialize state list in the Event Dispatcher Thread
        SwingUtilities.invokeLater(() -> stateList.addElement(ne));
    }

    private boolean myR(int x) {return fork[R(x)]==x;}
    private boolean myL(int x) {return fork[L(x)]==x;}

    private int R(int x) {return (4+x)%5;} //right fork of x
    private int L(int x) {return x;} //left fork of x
    private void fdrop(int x)  {fork[x]=5;}
    private void ftakeL(int x)  {fork[L(x)]=x;}
    private void ftakeR(int x)  {fork[R(x)]=x;}	
    private boolean free(int x){return fork[x]==5;}

    private void pre(String f, boolean cond, String msg) {
        if (cond) return;
        System.out.println("ERROR in "+f+": "+msg+"  STATE="+lastState); System.exit(1);
    }
    private void noForks(String f, int x) 		{pre(f,!myR(x) && !myL(x),x+" must not have any fork");}
    private void bothForks(String f, int x) 	{pre(f,myR(x) && myL(x),x+" must have both forks");}
    private void freeR(int x) 		{pre("takeR",free(R(x)),x+" right fork not free");}
    private void freeL(int x) 		{pre("takeL",free(L(x)),x+" left fork not free");}
    private void usedR(int x) 		{pre("wtakeR",!free(R(x)),x+" right fork is free");}
    private void usedL(int x) 		{pre("wtakeL",!free(L(x)),x+" left fork is free");}
    private void notMyR(int x) 		{pre("wtakeR",!myR(x),x+" right fork is yours");}
    private void notMyL(int x) 		{pre("wtakeL",!myL(x),x+" left fork is yours");}
    private void notBothFree(int x) 	{pre("wtakeLR",!free(R(x))||!free(L(x)),x+" both forks are free");}

    private void ownerL(int x) 		{pre("dropL",myL(x),x+" is not the owner of left fork");}

    //              0        1       2      3     4       5       6        7      8      9           10          11   12    13
    //states philo: inactive ponder, wtalk, talk, wtakeL, wtakeR, wtakeLR, talkR, talkL, wtakeLhasR, wtakeRhasL, eat, rest, restL
    private void step(int x, int n) {
        if (lastState.get(x)==n) return; //skip duplicates
        boolean[] f=new boolean[5]; 
        for(int i=0; i<5; i++) f[i]=free(i);
        final State ne = new State(lastState,x,n,f);
        lastState = ne;
        checkDeadlock(ne);
        // Update state list in the Event Dispatcher Thread
        SwingUtilities.invokeLater(() -> stateList.addElement(ne));
    }

    private void checkDeadlock(State e) {
        int n=0;
        for (int i=0; i<5;  i++) {int s=e.get(i); if (s==9 || s==10) n++;}
        if (n==5) {
            deadlock=true;
            done.countDown();
        }
    }

    public synchronized void begin(int x) 	{noForks("begin",x); step(x,1);}
    public synchronized void end(int x) 	{noForks("end",x);ended++;
        boolean endOK = (ended == 5);
        if (endOK) done.countDown();}
    public synchronized void enter(int x)   {noForks("enter",x); step(x,3);}
    public synchronized void exit(int x)    {noForks("exit",x); step(x,1);}
    public synchronized void wenter(int x)  {step(x,2);}
    public synchronized void eat(int x)	    {bothForks("eat",x); step(x,11);}
    public synchronized void ponder(int x)	{noForks("ponder",x); step(x,1);}
    public synchronized void wtakeR(int x)	{usedR(x); notMyR(x); step(x,myL(x)?10:5);}
    public synchronized void wtakeL(int x)  {usedL(x); notMyL(x); step(x,myR(x)?9:4);}
    public synchronized void wtakeLR(int x)	{noForks("wtakeLR",x); notBothFree(x); step(x,6);}
    public synchronized void takeR(int x) 	{freeR(x); ftakeR(x); step(x,myL(x)?11:7);}
    public synchronized void takeL(int x) 	{freeL(x); ftakeL(x); step(x,myR(x)?11:8);}
    public synchronized void takeLR(int x) 	{freeL(x); ftakeL(x); ftakeR(x); step(x,11);}
    public synchronized void dropR(int x)   {bothForks("dropR",x); fdrop(R(x)); step(x,13);}
    public synchronized void dropL(int x)   {ownerL(x); fdrop(L(x)); step(x,12);}

    public synchronized boolean rightUsed(int x)	{return !free(R(x));}
    public synchronized boolean leftUsed(int x) 	{return !free(L(x));}

    public void await() {
        // Wait until the end of the simulation
        try {
            done.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean deadlock() {return deadlock;}
}
