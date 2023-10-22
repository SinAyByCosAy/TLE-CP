//https://atcoder.jp/contests/dp/tasks/dp_b
package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class FrogTwoTabularDP {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int h[] = new int[n];
        int dp[] = new int[n];
        for(int i=0;i<n;i++){
            h[i] = sc.nextInt();
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=i+k;j++){
                if(j>=n)
                    break;
                dp[j] = Math.min(dp[j], Math.abs(h[j] - h[i]) + dp[i]);
            }
        }
        System.out.println(dp[n-1]);
    }
}
