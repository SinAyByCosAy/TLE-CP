//https://codeforces.com/problemset/problem/1221/D
package DPBootcamp.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MakeFenceGreatAgain {
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
        int q = fr.nextInt();
        while(q-- > 0){
            int n = fr.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for(int i = 0; i < n; i++){
                a[i] = fr.nextInt();
                b[i] = fr.nextInt();
            }
            long[][] dp = new long[n][3];
            for(long[] row : dp) Arrays.fill(row, -1 );
            out.println(getMinCost(a, b, dp, 0, 0, n));
            out.flush();
        }
    }
    public static long getMinCost(int[] a, int[] b, long[][] dp, int idx, int prevAdd, int n){
        if(idx == n) return 0;
        if(dp[idx][prevAdd] != -1) return dp[idx][prevAdd];

        long ans = Long.MAX_VALUE;
        for(int i = 0; i < 3; i++){//increase curr. block's height by 0, 1, 2 and get the min result
            if(idx == 0 || (a[idx - 1] + prevAdd != a[idx] + i))
                ans = Math.min(ans, 1l * i * b[idx] + getMinCost(a, b, dp, idx + 1, i, n));
        }

        return dp[idx][prevAdd] = ans;
    }
}
//TC: O(N)