package DPBootcamp.DP;

import java.util.Scanner;

//simpler version of Digit sum, we just need to count number of numbers <= N whose sum of digits = K
public class DigitSum2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int k = sc.nextInt();
        int n = num.length();
        int[][][] dp = new int[n][k + 1][2];
        for(int i = 0; i < n; i++) for(int j = 0; j <= k; j++) for(int l = 0; l <= 1; l++) dp[i][j][l] = -1;

        System.out.println(countNumsWithDigitSum(dp, num, k, 0, 0, 1));
    }
    public static int countNumsWithDigitSum(int[][][] dp, String num, int k, int idx, int currSum, int tight){
        if(currSum > k) return 0;
        int n = num.length();
        if(idx == n) return (currSum == k ? 1 : 0);
        if(dp[idx][currSum][tight] != -1) return dp[idx][currSum][tight];

        int count = 0;
        for(int i = 0; i <= (tight == 1 ? num.charAt(idx) - '0' : 9); i++)
            count += countNumsWithDigitSum(dp, num, k,
                    idx + 1, currSum + i, (tight == 1 && num.charAt(idx) - '0' == i ? 1 : 0));

        return dp[idx][currSum][tight] = count;
    }
}
//TC: O(|N| * K * 2 * 10)