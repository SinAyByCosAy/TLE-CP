//https://atcoder.jp/contests/dp/tasks/dp_o
package DPBootcamp.DP;

import java.util.Scanner;

public class Matching {
    static int mod = (int) 1e9 + 7;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) arr[i][j] = sc.nextInt();
        int pow = (1 << n);
        long[][] dp = new long[n][pow];
        for(int i = 0; i < n; i++) for(int j = 0; j < pow; j++) dp[i][j] = -1;
        System.out.println(getPairs(arr, dp, 0, 0));
    }
    public static long getPairs(int[][] arr, long[][] dp, int maleNo, int femaleMask){
        if(maleNo == arr.length) return 1; //found a successful pairing
        if(dp[maleNo][femaleMask] != -1) return dp[maleNo][femaleMask];

        long count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[maleNo][i] == 1 && ((femaleMask & (1 << i)) == 0))//check if female is compatible and available
                count = (count + getPairs(arr, dp, maleNo + 1, (femaleMask | (1 << i)))) % mod;
        }
        dp[maleNo][femaleMask] = count;
        return dp[maleNo][femaleMask];
    }
}
//TC: O(N^2 . 2^N)