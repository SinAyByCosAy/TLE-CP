//https://cses.fi/alon/task/1097/
package DPBootcamp.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RemovalGame {
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    public static void main(String[] args){
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = fr.nextInt();
        long[][] dp = new long[n][n];
        for(long[] row : dp) Arrays.fill(row, Long.MIN_VALUE);
        long[] psum = new long[n];
        psum[0] = arr[0];
        for(int i = 1; i < n; i++) psum[i] = psum[i - 1] + arr[i];
        out.println(maxScoreRecursive(arr, dp, psum, 0, n - 1));
        out.println(maxScoreIterative(arr, dp, psum, n));
        out.flush();
    }
    public static long maxScoreRecursive( int[] arr, long[][] dp, long[] psum, int i, int j){
        if(i == j) return arr[i];
        if(dp[i][j] != Long.MIN_VALUE) return dp[i][j];

        long leftPickRemainingSum = psum[j] - psum[i];
        long rightPickRemainingSum = (i == 0) ? psum[j - 1] : psum[j - 1] - psum[i - 1];
        dp[i][j] = Math.max(arr[i] + leftPickRemainingSum - maxScoreRecursive(arr, dp, psum, i + 1, j),
                arr[j] + rightPickRemainingSum - maxScoreRecursive(arr, dp, psum, i, j - 1));

        return dp[i][j];
    }
    public static long maxScoreIterative(int[] arr, long[][] dp, long[] psum, int n){
        for(int i = n - 1; i >= 0; i--){
            dp[i][i] = arr[i];
            for(int j = i + 1; j < n; j++){
                long leftPickRemainingSum = psum[j] - psum[i];
                long rightPickRemainingSum = (i == 0) ? psum[j - 1] : psum[j - 1] - psum[i - 1];
                dp[i][j] = Math.max(arr[i] + leftPickRemainingSum - dp[i + 1][j],
                        arr[j] + rightPickRemainingSum - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
}
