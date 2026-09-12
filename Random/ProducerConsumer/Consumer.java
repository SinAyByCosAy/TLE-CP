package DPBootcamp.Random.ProducerConsumer;

import java.util.Queue;

public class Consumer implements Runnable{
    int size;
    Queue<Object> shelf;
    Consumer(Queue<Object> shelf, int size){
        this.shelf = shelf;
        this.size = size;
    }
    @Override
    public void run(){
        while(true){
            synchronized(shelf) {
                if (shelf.size() > 0) {
                    System.out.println(shelf.size() + " " + Thread.currentThread().getName());
                    shelf.remove();
                }
            }
        }
    }
}
