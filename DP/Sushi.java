//https://atcoder.jp/contests/dp/tasks/dp_j
package DPBootcamp.DP;

import java.util.Arrays;
import java.util.Scanner;

public class Sushi {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] count = new int[4];
        for(int i = 0; i < n; i++){
            int ele = sc.nextInt();
            count[ele]++;
        }
        double[][][] dp = new double[303][303][303];
        for(double[][] rowCol : dp){
            for(double[] row : rowCol)
                Arrays.fill(row, -1);
        }
        System.out.println(getExpectedScore(dp, count[1], count[2], count[3], n));
    }
    public static double getExpectedScore(double[][][] dp, int j, int k, int l, int n){
        if(j == -1 || k == -1 || l == -1) return 0;
        if(j == 0 && k == 0 && l == 0) return 0;
        if(dp[j][k][l] >= 0) return dp[j][k][l];

        int i = n - (j + k + l);

        dp[j][k][l] = (1.0/(n - i) * (i
                + j * (1 + getExpectedScore(dp, j - 1, k, l, n))
                + k * (1 + getExpectedScore(dp, j + 1, k - 1, l, n))
                + l * (1 + getExpectedScore(dp, j, k + 1, l - 1, n))));

        return dp[j][k][l];
    }
}
//TC: O(N^3)