//https://codeforces.com/problemset/problem/1881/E
package DPBootcamp.DP;

import java.util.Arrays;
import java.util.Scanner;

public class BlockSeq {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
            int[] dp = new int[n + 1];
            Arrays.fill(dp, -1);
            dp[n - 1] = 1;
            dp[n] = 0;
            System.out.println(getMinRemovalsRecursion(arr, dp, 0, n));
            System.out.println(getMinRemovalsIterative(arr, dp, n));
        }
    }
    public static int getMinRemovalsRecursion(int[] arr, int[] dp, int i, int n){
        if(i == n || i == n - 1) return dp[i];
        if(dp[i] != -1) return dp[i];
        dp[i] = getMinRemovalsRecursion(arr, dp, i + 1, n) + 1;
        if(i + arr[i] + 1 <= n) dp[i] = Math.min(dp[i], getMinRemovalsRecursion(arr, dp, i + arr[i] + 1, n));

        return dp[i];
    }
    public static int getMinRemovalsIterative(int[] arr, int[] dp, int n){

        for(int i = n - 2; i >= 0; i--){
            dp[i] = dp[i + 1] + 1;
            if(i + arr[i] + 1 <= n) dp[i] = Math.min(dp[i], dp[i + arr[i] + 1]);
        }
        return dp[0];
    }
}
//TC: O(N)