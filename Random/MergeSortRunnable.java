package DPBootcamp.Random;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MergeSortRunnable implements Runnable{
    int[] arr;
    int start, end;
    ExecutorService ex;
    public MergeSortRunnable(int[] arr, int start, int end, ExecutorService ex){
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.ex = ex;
    }
    @Override
    public void run(){
        int n = end - start + 1;
        if(n == 1) return;
        int mid = (start + end) / 2;
       MergeSortRunnable sortLeft = new MergeSortRunnable(arr, start, mid, ex);
       MergeSortRunnable sortRight = new MergeSortRunnable(arr, mid + 1, end, ex);
       Future<?> leftFuture =  ex.submit(sortLeft);
       Future<?> rightFuture = ex.submit(sortRight);
       try {
           leftFuture.get();
           rightFuture.get();
       }catch (Exception E){
           throw new RuntimeException(E);
       }
       merge(arr, start, mid, end);
    }
    public void merge(int[] arr, int s, int m, int e){
        int[] c = new int[e - s + 1];
        int p1 = s, p2 = m + 1, p3 = 0;
        while(p1 <= m && p2 <= e){
            if(arr[p1] < arr[p2])
                c[p3++] = arr[p1++];
            else
                c[p3++] = arr[p2++];
        }
        while(p1 <= m) c[p3++] = arr[p1++];
        while(p2 <= e) c[p3++] = arr[p2++];
        p3 = 0;
        for(int i = s; i <= e; i++) arr[i] = c[p3++];
    }
}
