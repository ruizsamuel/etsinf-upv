package practica2;

public class LimitedTable extends RegularTable {

    private int count;
    private static final int MAX = 4;

    public LimitedTable(StateManager state) { super(state); count = 0; }

    public synchronized void enter(int id) throws InterruptedException {
        while (count >= MAX) { state.wenter(id); wait(); }
        count++;
        state.enter(id);
    }
    public synchronized void exit(int id)  {
        count--;
        state.exit(id);
        notifyAll();
    }
}
