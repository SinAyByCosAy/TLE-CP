//https://cses.fi/problemset/task/1634/
package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class MinimizeCoinsTabularDP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int coins[] = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        Arrays.sort(coins);
        int dp[] = new int[x + 1];
        dp[0] = 0;
        for(int i=0;i<=x;i++){
            int minCoins = dp[i];
            if(i!=0 && minCoins == 0) continue;
            for(int j=0;j<n;j++){
                int nextCoin = i + coins[j];
                if(nextCoin > x)
                    break;
                if(dp[nextCoin] != 0)
                    dp[nextCoin] = Math.min(dp[nextCoin], minCoins + 1);
                else
                    dp[nextCoin] = minCoins + 1;
            }
        }
        if(dp[x] == 0)
            System.out.println(-1);
        else
            System.out.println(dp[x]);
    }
}
