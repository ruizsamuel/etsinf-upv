package practica3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Terrain3 implements Terrain {
    Condition[][] conditions;
    ReentrantLock lock;

    Viewer v;
    public Terrain3 (int t, int ants, int movs, String msg) {
        v = new Viewer(t,ants,movs,msg);
        lock = new ReentrantLock();
        conditions = new Condition[t][t];
        for (int i = 0; i < t; i++) for (int j = 0; j < t; j++) conditions[i][j] = lock.newCondition();
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
            conditions[v.getPos(a).x][v.getPos(a).y].signalAll();
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
            Pos actual = v.getPos(a);
            while (v.occupied(dest)) {
                if (conditions[dest.x][dest.y].await(300, TimeUnit.MILLISECONDS)) v.retry(a);
                else {
                    v.chgDir(a);
                    return;
                }
            }
            v.go(a);
            conditions[actual.x][actual.y].signalAll();
        } finally {
            lock.unlock();
        }
    }
}