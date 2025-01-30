//https://codeforces.com/contest/2051/problem/E
package DPBootcamp.Contests.CF995Div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ProbE {
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
    }

    public static void solve() {
        FastReader in = new FastReader();
        int t = in.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = in.nextInt(); // number of customers
            int k = in.nextInt(); // max allowed negative reviews

            long[] a = new long[n]; // positive review thresholds
            long[] b = new long[n]; // max price thresholds

            // Read positive review thresholds
            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();
            }

            // Read max price thresholds
            for (int i = 0; i < n; i++) {
                b[i] = in.nextLong();
            }

            // Calculate maximum profit
            long maxProfit = 0;
            int noBuys = n;  // Initially everyone buys
            int negRating = 0;  // Initially no negative reviews

            // Using TreeMap to maintain sorted order
            TreeMap<Long, Integer> diffNegRating = new TreeMap<>();
            TreeMap<Long, Integer> diffNoBuys = new TreeMap<>();

            // Process positive review thresholds
            for (int i = 0; i < n; i++) {
                diffNegRating.put(a[i], diffNegRating.getOrDefault(a[i], 0) + 1);
            }

            // Process max price thresholds
            for (int i = 0; i < n; i++) {
                diffNegRating.put(b[i], diffNegRating.getOrDefault(b[i], 0) - 1);
                diffNoBuys.put(b[i], diffNoBuys.getOrDefault(b[i], 0) - 1);
            }

            // Process all price points
            for (Map.Entry<Long, Integer> entry : diffNegRating.entrySet()) {
                long price = entry.getKey();

                // Check if current negative ratings are within limit
                if (negRating <= k) {
                    maxProfit = Math.max(maxProfit, price * noBuys);
                }

                // Update negative ratings and number of buyers
                negRating += entry.getValue();
                noBuys += diffNoBuys.getOrDefault(price, 0);
            }

            System.out.println(maxProfit);
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
//TC: O(NlogN)