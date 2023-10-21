package DPBootcamp;

import java.util.Scanner;

public class CoinCombinationsI {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int coins[] = new int[n];
        for(int i=0;i<n;i++){
            coins[i] = sc.nextInt();
        }
        System.out.println(distinctWays(coins, n, x));
    }
    static int distinctWays(int[] coins, int n, int x){
        if(x == 0)
            return 1;
        if(x < 0)
            return 0;
        int ways = 0;
        for(int i=0;i<n;i++){
            ways += distinctWays(coins, n, x-coins[i]);
        }
        return ways;
    }
}
