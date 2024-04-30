//https://codeforces.com/problemset/problem/1498/C
package DPBootcamp.DPBootcamp;

import java.util.Scanner;

public class PlanarReflections {
    static int n, k, m = 1000 * 1000 * 1000 + 7;;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            n = sc.nextInt();
            k = sc.nextInt();
            long dp[][] = new long[n+1][k+1];
            for(int i=1;i<=n;i++){
                for(int j=1;j<=k;j++)
                    dp[i][j] = -1;
            }
            long ans = plane(dp, 1, k, 1);
            ans = (1 + ans) % m;
            System.out.println(ans);
        }
    }
    static long plane(long dp[][], int curr_plane, int k, int direction){
        if(curr_plane == 0 || k == 1 || curr_plane > n)
            return 0;
        if(dp[curr_plane][k] != -1)
            return dp[curr_plane][k];
        long ans;
        if(direction == 1){
            ans = (1 + (plane(dp, curr_plane - 1, k-1, 0) + plane(dp, curr_plane + 1, k, 1)) %m) %m;
        }else{
            ans = ((plane(dp, curr_plane - 1, k, 0) + plane(dp, curr_plane + 1, k-1, 1))%m + 1)%m;
        }
        dp[curr_plane][k] = ans;
        return ans;
    }
}
