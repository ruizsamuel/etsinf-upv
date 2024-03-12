package practica1;
public class Pool2 extends Pool {
    int k, i, ki;
    public void init(int ki, int cap) {
        i = k = 0;
        this.ki = ki;
    }
    public synchronized void kidSwims() throws InterruptedException{
        while (ki <= (k + 1) / i) {
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
        while ((i == 1 && k > 0) || ki <= k / (i - 1)) {
            log.waitingToRest();
            wait();
        }
        i--;
        notifyAll();
        log.resting();
    }
}
