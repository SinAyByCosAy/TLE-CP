package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class BookShop {
    static int[] h, s;
    static int[][] dp;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        h = new int[n+1];
        s = new int[n+1];
        for(int i=1;i<=n;i++){
            h[i] = sc.nextInt();
        }
        for(int i=1;i<=n;i++){
            s[i] = sc.nextInt();
        }
        dp = new int[n+1][x+1];
        for(int[] row:dp)
            Arrays.fill(row,-1);
        System.out.println(maxPages(n,x));
    }
    static int maxPages(int n, int amt){
        if(n == 0)
            return 0;
        if(dp[n][amt] != -1)
            return dp[n][amt];
        int ans = 0;
        if(amt - h[n] >= 0)
            ans = s[n] + maxPages(n-1, amt-h[n]);
        ans = Math.max(ans, maxPages(n-1, amt));
        dp[n][amt] = ans;
        return ans;
    }
}
