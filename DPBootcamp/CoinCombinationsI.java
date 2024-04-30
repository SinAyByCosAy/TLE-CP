//https://cses.fi/problemset/task/1635/
package DPBootcamp.DPBootcamp;

import java.util.Scanner;

public class CoinCombinationsI {
    static int m = 1000 * 1000 * 1000 + 7;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int coins[] = new int[n];
        for(int i=0;i<n;i++){
            coins[i] = sc.nextInt();
        }
        int dp[] = new int[x+1];
        for(int i=0;i<=x;i++){
            dp[i] = -1;
        }
        System.out.println(distinctWays(coins, n, x, dp));
    }
//    static int distinctWays(int[] coins, int n, int x){
//        if(x == 0)
//            return 1;
//        if(x < 0)
//            return 0;
//        int ways = 0;
//        for(int i=0;i<n;i++){
//            ways += distinctWays(coins, n, x-coins[i]);
//        }
//        return ways;
//    }
    static int distinctWays(int[] coins, int n, int x, int[] dp){
        if(x == 0)
            return 1;
        if(x < 0)
            return 0;
        if(dp[x] != -1)
            return dp[x];
        long ways = 0;
        for(int i=0;i<n;i++){
            ways = (ways + distinctWays(coins, n, x-coins[i], dp)) % m;
        }
        dp[x] = (int)ways;
        return dp[x];
    }
}
