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
        int dp[] = new int[k+1];
        long PSum[][] = new long[2][k+1];
        int flag = 0;
        for(int j=0;j<=a[1];j++)
            dp[j] = 1;
        PSum[flag][0] = 1;
        for(int j=1;j<=k;j++)
            PSum[flag][j] = PSum[flag][j-1] + dp[j];
        for(int i=2;i<=n;i++){
            for(int j=0;j<=k;j++){
                long sum = 0;
                int idx = j - a[i];
                if(idx <= 0)
                    sum = PSum[flag][j];
                else
                    sum = (PSum[flag][j] + m - PSum[flag][idx-1]) % m;
                dp[j] = (int)sum;
                PSum[flag^1][j] = (j == 0) ? dp[j] : (PSum[flag^1][j-1] + dp[j])%m;
            }
            flag ^= 1;
        }
        System.out.println(dp[k]);
    }
}
