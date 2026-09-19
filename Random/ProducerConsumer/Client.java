package DPBootcamp.Random.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Client {
    public static void main(String args[]){
        Queue<Object> shelf = new LinkedList<>();
        int size = 5;
        Semaphore ps = new Semaphore(size);
        Semaphore cs = new Semaphore(0);
        Producer producer = new Producer(shelf, size, ps, cs);
        Consumer consumer = new Consumer(shelf, size, ps, cs);
        Thread p1 = new Thread(producer);
        Thread p2 = new Thread(producer);
        Thread p3 = new Thread(producer);
        Thread p4 = new Thread(producer);
        Thread p5 = new Thread(producer);

        Thread c1 = new Thread(consumer);
        Thread c2 = new Thread(consumer);
        Thread c3 = new Thread(consumer);
        Thread c4 = new Thread(consumer);
        Thread c5 = new Thread(consumer);
        Thread c6 = new Thread(consumer);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
    }
}
