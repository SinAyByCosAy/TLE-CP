//https://leetcode.com/problems/longest-increasing-subsequence/description/
package DPBootcamp.DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubseqTabularDP {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int A[] = new int[n];
        for(int i=0;i<n;i++){
            A[i] = sc.nextInt();
        }
        int dp[] = new int[n];
        dp[0] = 1;
        for(int i=0;i<n;i++){
            LIS(A, dp, i);
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(max);
    }
    static void LIS(int[] A, int[] dp, int i){
        int ans = 1;
        for(int j=0;j<i;j++){
            if(A[j] < A[i]){
                ans = Math.max(ans, 1 + dp[j]);
            }
        }
        dp[i] = ans;
    }
}
