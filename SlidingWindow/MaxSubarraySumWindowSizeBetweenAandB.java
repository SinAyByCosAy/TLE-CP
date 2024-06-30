//https://cses.fi/problemset/task/1644
package DPBootcamp.SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MaxSubarraySumWindowSizeBetweenAandB {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        long maxSum = Long.MIN_VALUE;
        long[] PS = new long[n+1];
        for(int i = 1; i <= n; i++){
            PS[i] = PS[i - 1] + arr[i-1];
        }
        Deque<Long> dq = new LinkedList<>();
        for(int i = a; i <= n; i++){
            while(!dq.isEmpty() && dq.peekLast() > PS[i - a]){
                dq.removeLast();
            }
            dq.addLast(PS[i - a]);

            if(i > b){
                int removeIdx = i - b - 1;
                if(!dq.isEmpty() && dq.peekFirst() == PS[removeIdx]){
                    dq.removeFirst();
                }
            }
            long sum = PS[i] - dq.peekFirst();
            maxSum = Math.max(maxSum, sum);
        }
        System.out.println(maxSum);
    }
}
