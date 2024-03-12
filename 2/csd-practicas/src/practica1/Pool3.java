package practica1;

public class Pool3 extends Pool {
    int k, i, ki, cap;
    public void init(int ki, int cap) {
        i = k = 0;
        this.ki = ki;
        this.cap = cap;
    }
    public synchronized void kidSwims() throws InterruptedException{
        while (ki <= (k + 1) / i || (k + i + 1) > cap) {
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
    public synchronized void instructorSwims() throws InterruptedException{
        while (k + i + 1 > cap) {
            log.waitingToSwim();
            wait();
        }
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