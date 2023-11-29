//https://atcoder.jp/contests/dp/tasks/dp_m
package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class Candies {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = 1000 * 1000 * 1000 + 7;
        int a[] = new int[n+1];
        for(int i=1;i<=n;i++){
            a[i] = sc.nextInt();
        }
        int dp[][] = new int[n+1][k+1];
        for(int j=0;j<=a[1];j++)
            dp[1][j] = 1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<=k;j++){
                long sum = 0;
                for(int pick=0;pick<=a[i];pick++){
                    if(j-pick < 0) break;
                    sum = (sum + dp[i-1][j-pick]) % m;
                }
                dp[i][j] = (int)sum;
            }
        }
        System.out.println(dp[n][k]);
        for(int[] row: dp)
            System.out.println(Arrays.toString(row));
    }
}
