//https://cses.fi/problemset/task/1634/
package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class MinimizeCoins {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int coins[] = new int[n];
        for(int i=0;i<n;i++){
            coins[i] = sc.nextInt();
        }
        Arrays.sort(coins);
        int dp[] = new int[x+1];
        for(int i=0;i<=x;i++){
            dp[i] = -1;
        }
        int minCoins = noOfCoins(n, x, coins, dp);
        if(minCoins == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minCoins);
    }
//    static int noOfCoins(int n, int x, int[] coins){
//        if(x == 0)
//            return 0;
//        int minCoins = Integer.MAX_VALUE;
//        for(int i=0;i<n;i++){
//            if(x-coins[i] < 0)
//                break;
//            else
//                minCoins = Math.min(minCoins, noOfCoins(n, x-coins[i], coins));
//        }
//        if(minCoins == Integer.MAX_VALUE)
//            return minCoins;
//        return minCoins + 1;
//    }
    static int noOfCoins(int n, int x, int[] coins, int[] dp){
        if(x == 0)
            return 0;
        if(dp[x] != -1){
            return dp[x];
        }
        int minCoins = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(x-coins[i] < 0)
                break;
            else
                minCoins = Math.min(minCoins, noOfCoins(n, x-coins[i], coins, dp));
        }
        if(minCoins == Integer.MAX_VALUE){
            dp[x] = minCoins;
        }else{
            dp[x] = minCoins + 1;
        }
        return dp[x];
    }
}
