package DPBootcamp.Random;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NumberPrinterCallableExecutor {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for(int i = 0; i <=100; i++){
            NumberPrinterCallable np = new NumberPrinterCallable(i);
            Future<String> future = ex.submit(np);
            System.out.println("Main : " + future.get());
        }
        ex.shutdown();
    }
}
