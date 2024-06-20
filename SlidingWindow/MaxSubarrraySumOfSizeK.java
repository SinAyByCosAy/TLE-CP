package DPBootcamp.SlidingWindow;

import java.util.Scanner;

public class MaxSubarrraySumOfSizeK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt(); //window size
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            //STEP 1: add new element
            sum += arr[i];

            //STEP 2: remove first element if window size is greater than k
            if(i >= k){
                sum -= arr[i - k];
            }

            //STEP 3: update answer, from the very first SA window ending @ (k-1) and then for every window
            if(i >= k - 1){
                maxSum = Math.max(maxSum, sum);
            }
        }
        System.out.println(maxSum);
    }
}
