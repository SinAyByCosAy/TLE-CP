//https://atcoder.jp/contests/dp/tasks/dp_e
package DPBootcamp.DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class Knapsack2 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int weight[] = new int[n+1];
        int value[] = new int[n+1];
        int sum = 0;
        for(int i=1;i<=n;i++){
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
            sum += value[i];
        }
        long dp[][] = new long[n+1][sum+1];
        for(long[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);
        dp[0][0] = 0;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=sum;j++){
                dp[i][j] = dp[i-1][j];
                if(j - value[i] >= 0){
                    dp[i][j] = Math.min(dp[i][j], weight[i] + dp[i-1][j-value[i]]);
                }
            }
        }
        int ans = 0;
        for(int k=1;k<=sum;k++)
            if(dp[n][k] <= W)
                ans = k;
        System.out.println(ans);
    }
}
