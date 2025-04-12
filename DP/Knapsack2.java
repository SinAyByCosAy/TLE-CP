//https://atcoder.jp/contests/dp/tasks/dp_e
package DPBootcamp.DP;

import java.util.Arrays;
import java.util.Scanner;

public class Knapsack2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int[] weights = new int[n + 1];
        int[] value = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            weights[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }
        int limit = (int) 1e5;
        int[][] dp = new int[n + 1][limit + 1];
        for(int[] row : dp) Arrays.fill(row, w + 1); //setting value that can't be answer
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
            dp[i][0] = 0; //for 0 value, we need 0 weight
            for(int j = 1; j <= limit; j++){
                dp[i][j] = dp[i - 1][j]; //don't pick current element
                if(j >= value[i] && weights[i] + dp[i - 1][j - value[i]] <= w)
                    dp[i][j] = Math.min(dp[i][j], weights[i] + dp[i - 1][j - value[i]]); //pick current element
            }
        }
        //answer is the largest value for which weight <= w
        for(int j = limit; j >= 0; j--){
            if(dp[n][j] <= w){
                System.out.println(j);
                break;
            }
        }
    }
}
//TC: O(N.V)