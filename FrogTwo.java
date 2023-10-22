//https://atcoder.jp/contests/dp/tasks/dp_b
package DPBootcamp;

import java.util.Scanner;

public class FrogTwo {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int height[] = new int[n];
        for(int i=0;i<n;i++) {
            height[i] = sc.nextInt();
        }
        int dp[] = new int[n];
        for(int i=0;i<n;i++)
            dp[i] = -1;
        System.out.println(minCost(dp, height, k, n-1));
    }

    //brute recursion
//    static int minCost(int[] h, int k, int stone){
//        if(stone == 0)
//            return 0;
//        int ans = Integer.MAX_VALUE;
//        for(int i=stone-1;i>=stone-k;i--){
//            if(i<0)
//                break;
//            ans = Math.min(ans, Math.abs(h[stone] - h[i]) + minCost(h, k, i));
//        }
//        return ans;
//    }
    static int minCost(int[] dp, int[] h, int k, int stone){
        if(stone == 0)
            return 0;
        if(dp[stone] != -1)
            return dp[stone];

        int ans = Integer.MAX_VALUE;
        for(int i=stone-1;i>=stone-k;i--){
            if(i<0)
                break;
            ans = Math.min(ans, Math.abs(h[stone] - h[i]) + minCost(dp, h, k, i));
        }
        dp[stone] = ans;
        return dp[stone];
    }
}
