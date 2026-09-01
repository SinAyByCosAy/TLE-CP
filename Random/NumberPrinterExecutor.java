package DPBootcamp.Random;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberPrinterExecutor {
    public static void main(String[] args){
//        ExecutorService ex = Executors.newSingleThreadExecutor();
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for(int i = 0; i <= 100; i++){
            NumberPrinter np = new NumberPrinter(i);
            ex.submit(np);
        }
        ex.shutdown();
    }
}
