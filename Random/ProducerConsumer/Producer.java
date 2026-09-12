package DPBootcamp.Random.ProducerConsumer;

import java.util.Queue;

public class Producer implements Runnable{
    int size;
    Queue<Object> shelf;
    Producer(Queue<Object> shelf, int size){
        this.shelf = shelf;
        this.size = size;
    }
    @Override
    public void run(){
        while(true){
            synchronized (shelf) {
                if (shelf.size() < size) {
                    System.out.println(shelf.size() + " " + Thread.currentThread().getName());
                    shelf.add(new Object());
                }
            }
        }
    }
}
