package practica3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Terrain1 implements Terrain {

    Condition condition;
    ReentrantLock lock;

    Viewer v;
    public Terrain1 (int t, int ants, int movs, String msg) {
        v = new Viewer(t,ants,movs,msg);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }
    @Override
    public void hi(int a) {
        try {
            lock.lock();
            v.hi(a);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void bye(int a) {
        try {
            lock.lock();
            condition.signalAll();
            v.bye(a);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void move(int a) throws InterruptedException {
        try {
            lock.lock();
            v.turn(a);
            Pos dest = v.dest(a);
            while (v.occupied(dest)) {
                condition.await();
                v.retry(a);
            }
            v.go(a);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
