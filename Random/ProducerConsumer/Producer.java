package DPBootcamp.Random.ProducerConsumer;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable{
//    int size;
//    Queue<Object> shelf;
//    Semaphore ps, cs;
//    Producer(Queue<Object> shelf, int size, Semaphore ps, Semaphore cs){
//        this.shelf = shelf;
//        this.size = size;
//        this.ps = ps;
//        this.cs = cs;
//    }

    BlockingQueue<Object> queue;
    Producer(BlockingQueue<Object> queue){
        this.queue = queue;
    }
    @Override
    public void run(){
        while(true){
            try{
                queue.put(new Object());
                System.out.println("Producer: "+Thread.currentThread().getName()+" size: "+queue.size());
            }catch (Exception e){
                System.out.println(e);
            }
//            try {
//                ps.acquire();
//                System.out.println(shelf.size() + " " + Thread.currentThread().getName());
//                shelf.add(new Object());
//                cs.release();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}
