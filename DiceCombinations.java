//https://cses.fi/problemset/task/1633/
package DPBootcamp;

import java.util.Scanner;

public class DiceCombinations {
    static int m = 1000 * 1000 * 1000 + 7;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long dp[] = new long[n+1];
        for(int i=0;i<=n;i++){
            dp[i] = -1;
        }
        System.out.println((int)ways(n, dp));
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
    static long ways(int n, long[] dp){
        if(n==0||n==1)
            return 1;
        if(dp[n]!=-1)
            return dp[n];
        long count = 0;
        for(int i=1;i<=6 & n-i>=0;i++){
            count = (count + ways(n-i, dp)) % m;
        }
        dp[n] = count;
        return dp[n];
    }
}
