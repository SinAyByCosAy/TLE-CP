package DPBootcamp.Random.ProducerConsumer;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable{
//    int size;
//    Queue<Object> shelf;
//    Semaphore ps, cs;
//    Consumer(Queue<Object> shelf, int size, Semaphore ps, Semaphore cs){
//        this.shelf = shelf;
//        this.size = size;
//        this.ps = ps;
//        this.cs = cs;
//    }

    BlockingQueue<Object> queue;
    Consumer(BlockingQueue<Object> queue){
        this.queue = queue;
    }
    @Override
    public void run(){
        while(true){
            try{
                queue.take();
                System.out.println("Consumer: "+Thread.currentThread().getName()+" size: "+queue.size());
            }catch (Exception e){
                System.out.println(e);
            }
//            try {
//                cs.acquire();
//                System.out.println(shelf.size() + " " + Thread.currentThread().getName());
//                shelf.remove();
//                ps.release();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}
