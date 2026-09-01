package DPBootcamp.Random;

import java.util.concurrent.Callable;

public class NumberPrinterCallable implements Callable<String> {
    int num;
    NumberPrinterCallable(int num){
        this.num = num;
    }
    @Override
    public String call() throws Exception{
        String s = num + " : " + Thread.currentThread().getName();
        return s;
    }
}
