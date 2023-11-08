//https://codeforces.com/problemset/problem/1625/C
package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class RoadOptimization {
    public static final int inf = Integer.MAX_VALUE, N = 505;
    static int x[], a[];
    static long dp[][];
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int k = sc.nextInt();
        x = new int[N];
        a = new int[N];
        dp = new long[N][N];
        for(int i=1;i<=n;i++)
            x[i] = sc.nextInt();
        for(int i=1;i<=n;i++)
            a[i] = sc.nextInt();
        x[n+1] = l;
        for(long[] row: dp)
            Arrays.fill(row, -1);
        long ans = inf;
        for(int i=0;i<=k;i++) {
            ans = Math.min(ans, getMinTime(n + 1, i));
        }
        System.out.println(ans);
    }
    static long getMinTime(int i, int j){
        if(i == 1){
            if(j == 0)
                return 0;
            return inf;
        }
        if(dp[i][j] != -1)
            return dp[i][j];
        long ans = inf;
        for(int prev = i-1;prev>=1;prev--){
            int remove = i - prev - 1;
            if(j - remove >= 0)
                ans = Math.min(ans, getMinTime(prev, j - remove) + 1l * (a[prev] * (x[i] - x[prev])));
        }
        dp[i][j] = ans;
        return ans;
    }
}
