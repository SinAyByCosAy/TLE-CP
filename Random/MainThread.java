package DPBootcamp.Random;

public class MainThread {
    public static void main(String args[]){
        System.out.println("Printing from the: "+Thread.currentThread().getName()+" thread");


        HelloworldPrinter hwp = new HelloworldPrinter();
        Thread t = new Thread(hwp);
        t.start();
        doPrint();
    }

    private static void doPrint(){
        System.out.println("Hello from the other side: "+Thread.currentThread().getName());
    }
}
