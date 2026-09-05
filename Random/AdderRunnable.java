package DPBootcamp.Random;

import java.util.concurrent.locks.Lock;

public class AdderRunnable implements Runnable{
    CounterShareable cs;
    Lock lock;
    AdderRunnable(CounterShareable cs, Lock lock){
        this.cs = cs;
        this.lock = lock;
    }
    @Override
    public void run(){
        for(int i = 0; i <= 10000; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " " + "Count="+cs.var);
            cs.var += i;
            lock.unlock();
        }
    }
}
