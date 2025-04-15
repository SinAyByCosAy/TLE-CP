//Strange Knight - ICPC
package DPBootcamp.DP;

import java.util.Scanner;

public class StrangeKnight {
    static int mod = (int) 1e9 + 7;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] x_move = new int[n + 1];
            int[] y_move = new int[n + 1];
            for(int i = 1; i <= n; i++){
                x_move[i] = sc.nextInt();
                y_move[i] = sc.nextInt();
            }
            long[][] curr_dp = new long[257][257];
            long[][] next_dp = new long[257][257];
            curr_dp[1][1] = 1;
            calculatePSUM(curr_dp, 257, 257);
            for(int op = 1; op <= n; op++){
                for(int i = 1; i < 257; i++){
                    for(int j = 1; j < 257; j++){
                        int x = x_move[op];
                        int y = y_move[op];
                        int row_top = i - x < 1 ? 1 : i - x;
                        int row_bottom = i + x > 256 ? 256 : i + x;
                        int col_left = j - y < 1 ? 1 : j - y;
                        int col_right = j + y > 256 ? 256 : j + y;
                        next_dp[i][j] = (curr_dp[row_bottom][col_right] - curr_dp[row_top - 1][col_right]
                                - curr_dp[row_bottom][col_left - 1] + curr_dp[row_top - 1][col_left - 1]
                                + mod + mod) % mod;
                    }
                }
                swap(curr_dp, next_dp, 257, 257);
                calculatePSUM(curr_dp, 257, 257);
            }
            System.out.println(curr_dp[1][1]);
        }
    }
    public static void calculatePSUM(long[][] curr_dp, int n, int m){
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                curr_dp[i][j] = (curr_dp[i - 1][j] + curr_dp[i][j - 1] - curr_dp[i - 1][j - 1] + curr_dp[i][j] + mod) % mod;
            }
        }
    }
    public static void swap(long[][] curr_dp, long[][] next_dp, int n, int m){
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++)
                curr_dp[i][j] = next_dp[i][j];
        }
    }
}
//TC: O(256 * 256 * 1000)