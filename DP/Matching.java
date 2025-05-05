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
        long[] dp = new long[pow];
        for(int j = 0; j < pow; j++) dp[j] = -1;
        System.out.println(getPairs(arr, dp, 0));
    }
    public static long getPairs(int[][] arr, long[] dp, int femaleMask){
        int maleNo = countSetBits(femaleMask);
        if(maleNo == arr.length) return 1; //found a successful pairing
        if(dp[femaleMask] != -1) return dp[femaleMask];

        long count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[maleNo][i] == 1 && ((femaleMask & (1 << i)) == 0))//check if female is compatible and available
                count = (count + getPairs(arr, dp, (femaleMask | (1 << i)))) % mod;
        }
        dp[femaleMask] = count;
        return dp[femaleMask];
    }
    public static int countSetBits(int mask){
        int count = 0;
        while(mask > 0){
            if((mask & 1) == 1) count++;
            mask >>= 1;
        }
        return count;
    }
}
//TC: O(N . 2^N)
//Used state elimination to make dp[maleNo][femaleMask] -> dp[femaleMask]
//as maleNo can be derived from female mask(#set bits)