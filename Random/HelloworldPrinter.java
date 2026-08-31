package DPBootcamp.Random;

public class HelloworldPrinter implements Runnable{
    @Override
    public void run(){
        System.out.println("Bhaiya ji thread me aa gaye hai, ye hai: "+Thread.currentThread().getName());
    }
}
