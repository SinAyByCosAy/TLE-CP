package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class CoveredPath {
    static int dp[][], v1, v2, d;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        v1 = sc.nextInt();
        v2 = sc.nextInt();
        int t = sc.nextInt();
        d = sc.nextInt();
        dp = new int[105][1200];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        System.out.println(getMaxDist(t,v2));
    }
    static int getMaxDist(int t, int x){
        if(t==1){
            if(x != v1)
                return Integer.MIN_VALUE;
            else
                return v1;
        }
        if(dp[t][x] != -1)
            return dp[t][x];
        int ans = Integer.MIN_VALUE;
        for(int i=x-d;i<=x+d;i++){
            if(i>=0)
            ans = Math.max(ans, getMaxDist(t-1, i));
        }
        ans += x;
        dp[t][x] = ans;
        return ans;
    }
}
