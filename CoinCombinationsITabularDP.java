//https://cses.fi/problemset/task/1635/
package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class CoinCombinationsITabularDP {
    static int m = 1000 * 1000 * 1000 + 7;
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
        dp[0] = 1;
        for(int i=0;i<=x;i++){
            if(i!=0 && dp[i] == 0)
                continue;
            long ways = dp[i];
            for(int j=0;j<n;j++){
                if(i + coins[j] > x)
                    break;
                dp[i + coins[j]] = (int)(1l * dp[i + coins[j]] + ways) % m;
            }
        }
        System.out.println(dp[x]);
    }
}
