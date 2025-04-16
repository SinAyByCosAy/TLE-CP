//given an array that contains 3, 5 and ?
//and array that carries weights for value 3, and array with weights for value 5 corresponding to each index.
//we have to replace ? with either 3 or 5 in such a way that we get the max score by picking their respective weights at that index
//and #threesPicked <= k1 & #fivesPicked <= k2
package DPBootcamp.DP;

import java.util.Arrays;
import java.util.Scanner;

public class ThreesFivesQuestionMarks {
    static int k1, k2;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] three = new int[n];
        int[] five = new int[n];
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        for(int i = 0; i < n; i++) three[i] = sc.nextInt();
        for(int i = 0; i < n; i++) five[i] = sc.nextInt();
        k1 = sc.nextInt();
        k2 = sc.nextInt();
        int[][] dp = new int[n][n];
        for(int[] row : dp) Arrays.fill(row, -1);
        System.out.println(getMaxScoreRecurr(arr, three, five, dp, 0, 0, n));
        System.out.println(getMaxScoreIterative(arr, three, five));
    }
    public static int getMaxScoreRecurr(int[] arr, int[] three, int[] five, int[][] dp, int i, int threesUsed, int n){
        int fivesUsed = i - threesUsed;
        if(threesUsed > k1 || fivesUsed > k2) return Integer.MIN_VALUE;
        if(i == n) return 0;
        if(dp[i][threesUsed] != -1) return dp[i][threesUsed];

        if(arr[i] == 3) dp[i][threesUsed] = three[i] + getMaxScoreRecurr(arr, three, five, dp, i + 1, threesUsed + 1, n);
        if(arr[i] == 5) dp[i][threesUsed] = five[i] + getMaxScoreRecurr(arr, three, five, dp, i + 1, threesUsed, n);
        if(arr[i] == -1) dp[i][threesUsed] = Math.max(
                three[i] + getMaxScoreRecurr(arr, three, five, dp, i + 1, threesUsed + 1, n),
                five[i] + getMaxScoreRecurr(arr, three, five, dp, i + 1, threesUsed, n)
        );
        return dp[i][threesUsed];
    }
    public static int getMaxScoreIterative(int[] arr, int[] three, int[] five){
        int n = arr.length;
        int dp[][] = new int[n + 1][n + 1];
        for(int i = n - 1; i >= 0; i--){
            for(int j = i; j >= 0; j--){
                int fivesUsed = i - j;
                if(j > k1 || fivesUsed > k2) dp[i][j] = Integer.MIN_VALUE;
                else{
                    if(arr[i] == 3) dp[i][j] = three[i] + dp[i + 1][j + 1];
                    else if(arr[i] == 5) dp[i][j] = five[i] + dp[i + 1][j];
                    else dp[i][j] = Math.max(
                            three[i] + dp[i + 1][j + 1],
                            five[i] + dp[i + 1][j]
                        );
                }
            }
        }
        return dp[0][0];
    }
}
//TC: O(N ^ 2)