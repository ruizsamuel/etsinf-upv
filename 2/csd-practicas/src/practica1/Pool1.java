package practica1;

public class Pool1 extends Pool {
    int k, i;
    public void init(int ki, int cap) {
        i = k = 0;
    }
    public synchronized void kidSwims() throws InterruptedException{
        while (i < 1) {
            log.waitingToSwim();
            wait();
        }
        k++;
        notifyAll();
        log.swimming();
    }
    public synchronized void kidRests() {
        k--;
        notifyAll();
        log.resting();
    }
    public synchronized void instructorSwims() {
        i++;
        notifyAll();
        log.swimming();
    }
    public synchronized void instructorRests() throws InterruptedException{
        while (i < 2 && k > 0) {
            log.waitingToRest();
            wait();
        }
        i--;
        notifyAll();
        log.resting();
    }
}