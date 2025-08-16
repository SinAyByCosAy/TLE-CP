package DPBootcamp.Random;

import java.util.*;

public class MinimizeMaxAdjDifference {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        long start = 0, end = (int) 2e9;
        long ans = -1;
        while(start <= end){
            long mid = (start + end) / 2;
            if(check(arr, mid, n, k)){
                ans = mid;
                end = mid - 1;
            }else start = mid + 1;
        }
        System.out.println(ans);
    }
    public static boolean check(int[] arr, long target, int n, int k){
        int[] dp = new int[n];
        int ans = 0;
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(Math.abs(arr[i] - arr[j]) <= target * (i - j))
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return (n - ans <= k);
    }
}
//TC: O(N^2 * logN)