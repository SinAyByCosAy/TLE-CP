package DPBootcamp.Random.ProducerConsumer;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable{
    int size;
    Queue<Object> shelf;
    Semaphore ps, cs;
    Consumer(Queue<Object> shelf, int size, Semaphore ps, Semaphore cs){
        this.shelf = shelf;
        this.size = size;
        this.ps = ps;
        this.cs = cs;
    }
    @Override
    public void run(){
        while(true){
            try {
                cs.acquire();
                System.out.println(shelf.size() + " " + Thread.currentThread().getName());
                shelf.remove();
                ps.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
