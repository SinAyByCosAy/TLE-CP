//https://atcoder.jp/contests/dp/tasks/dp_s
package DPBootcamp.DP;

import java.util.Scanner;

public class DigitSum {
    static int mod = (int)1e9 + 7;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int n = num.length();
        int d = sc.nextInt();
        long[][][] dp = new long[n][d][2];
        for(int i = 0; i < n; i++) for(int j = 0; j < d; j++) for(int k = 0; k <= 1; k++) dp[i][j][k] = -1;

        System.out.println((countDigitSums(num, d, dp, n, 0, 0, 1) - 1 + mod) % mod);
    }
    public static long countDigitSums(String num, int d, long[][][] dp, int n, int idx, int currSum, int tight){
        if(idx == n) return (currSum == 0) ? 1 : 0;
        if(dp[idx][currSum][tight] != -1) return dp[idx][currSum][tight];

        long count = 0;
        for(int i = 0; i <= (tight == 1 ? num.charAt(idx) - '0' : 9); i++)
            count = (count + countDigitSums(num, d, dp, n, idx + 1, (currSum + i) % d,
                    (tight == 1 && num.charAt(idx) - '0' == i) ? 1 : 0)) % mod;
        return dp[idx][currSum][tight] = count;
    }
}
//TC: O(|N| * D * 2 * 10)