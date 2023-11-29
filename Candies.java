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
        long PSum[][] = new long[n+1][k+1];
        for(int j=0;j<=a[1];j++)
            dp[1][j] = 1;
        PSum[1][0] = dp[1][0];
        for(int j=1;j<=k;j++)
            PSum[1][j] = PSum[1][j-1] + dp[1][j];
        for(int i=2;i<=n;i++){
            for(int j=0;j<=k;j++){
                long sum = 0;
                int idx = j - a[i];
                if(idx <= 0)
                    sum = PSum[i-1][j];
                else
                    sum = PSum[i-1][j] - PSum[i-1][idx-1];
                dp[i][j] = (int)sum;
                PSum[i][j] = (j == 0) ? dp[i][j] : (PSum[i][j-1] + dp[i][j])%m;
            }
        }
        System.out.println(dp[n][k]);
//        for(int[] row: dp)
//            System.out.println(Arrays.toString(row));
//        System.out.println();
//        for(int[] row: PSum)
//            System.out.println(Arrays.toString(row));
    }
}
