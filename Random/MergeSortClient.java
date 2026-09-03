package DPBootcamp.Random;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSortClient {
    public static void main(String[] args){
        int[] arr = {7, 3, 9, 2, 1, 4, 1, 6};
        ExecutorService ex = Executors.newCachedThreadPool();
        MergeSortRunnable sort = new MergeSortRunnable(arr, 0, arr.length - 1, ex);
        Future<?> future = ex.submit(sort);
        try{
            future.get();
        }catch (Exception E){
            throw new RuntimeException(E);
        }
        ex.shutdown();
        System.out.println(Arrays.toString(arr));
    }
}
