//https://cses.fi/problemset/task/1633/
package DPBootcamp;

import java.util.Scanner;

public class DiceCombinations {
    static int m = 1000 * 1000 * 1000 + 7;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int dp[] = new int [n+1];
        for(int i=0;i<=n;i++){
            dp[i] = -1;
        }
        System.out.println(ways(n, dp));
    }
//    static int ways(int n){
//        if(n==0||n==1)
//            return 1;
//        int count = 0;
//        for(int i=1;i<=6 & n-i>=0;i++){
//            count += ways(n-i);
//        }
//        return count;
//    }
    static int ways(int n, int[] dp){
        if(n==0||n==1)
            return 1;
        if(dp[n]!=-1)
            return dp[n];
        long count = 0;
        for(int i=1;i<=6 & n-i>=0;i++){
            count = (count + ways(n-i, dp)) % m;
        }
        dp[n] = (int)(count % m);
        return dp[n];
    }
}
