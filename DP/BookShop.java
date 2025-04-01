//https://cses.fi/problemset/task/1158/
package DPBootcamp.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BookShop {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
    public static void main(String[] args){
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        int x = fr.nextInt();
        int[] prices = new int[n];
        int[] pages = new int[n];
        for(int i = 0; i < n; i++) prices[i] = fr.nextInt();
        for(int i = 0; i < n; i++) pages[i] = fr.nextInt();
        int[][] dp = new int[n + 1][x + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        for(int i = 0; i <= x; i++) dp[n][i] = 0;
        out.println(getMaxPagesRecursive(pages, prices, dp, n, 0, x));
        out.println(getMaxPagesIterative(pages, prices, n, x));
        out.flush();
    }
    public static int getMaxPagesRecursive(int[] pages, int[] prices, int[][] dp, int n, int i, int k){
        if(i == n) return 0;
        if(dp[i][k] != -1) return dp[i][k];

        dp[i][k] = getMaxPagesRecursive(pages, prices, dp, n, i + 1, k);
        if(k - prices[i] >= 0) dp[i][k] = Math.max(dp[i][k], pages[i] + getMaxPagesRecursive(pages, prices, dp, n, i + 1, k - prices[i]));

        return dp[i][k];
    }

    public static int getMaxPagesIterative(int[] pages, int[] prices, int n, int x){
        int[][] dp = new int[2][x + 1];//need current and next row only
        for(int i = 0; i <= x; i++) dp[0][i] = 0;
        int flag = 1;//used to switch between rows
        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j <= x; j++){
                dp[flag][j] = dp[flag ^ 1][j];
                if(j - prices[i] >= 0) dp[flag][j] = Math.max(dp[flag][j], pages[i] + dp[flag ^ 1][j - prices[i]]);
            }
            flag ^= 1; //row switching
        }
        return dp[flag ^ 1][x]; //bit will get flipped one last time in the end and point to the other row
    }
}
//TC: O(N.X)
//SC: O(X)