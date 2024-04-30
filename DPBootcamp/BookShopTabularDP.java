//https://cses.fi/problemset/task/1158
package DPBootcamp.DPBootcamp;

import java.util.Scanner;

public class BookShopTabularDP {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] h = new int[n+1];
        int[] s = new int[n+1];
        for(int i=1;i<=n;i++){
            h[i] = sc.nextInt();
        }
        for(int i=1;i<=n;i++){
            s[i] = sc.nextInt();
        }
        int flag = 1;
        int[][]dp = new int[2][x+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=x;j++){
                int ans = 0;
                if(j - h[i] >= 0)
                    ans = s[i] + dp[flag^1][j-h[i]];
                ans = Math.max(ans, dp[flag^1][j]);
                dp[flag][j] = ans;
            }
            flag ^= 1;
        }
        System.out.println(dp[flag^1][x]);
    }
}
