package DPBootcamp.Random;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AdderSubtractClient {
    public static void main(String[] args) throws InterruptedException {
        CounterShareable cs = new CounterShareable();
        Lock lock = new ReentrantLock();

        AdderRunnable ar = new AdderRunnable(cs, lock);
        SubtractRunnable sr = new SubtractRunnable(cs, lock);
        Thread t1 = new Thread(ar);
        Thread t2 = new Thread(sr);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(cs.var);
    }
}
