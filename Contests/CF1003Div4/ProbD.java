//https://codeforces.com/contest/2065/problem/D
package DPBootcamp.Contests.CF1003Div4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProbD {
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
        int t = fr.nextInt();
        while(t-- > 0){
            int n = fr.nextInt();
            int m = fr.nextInt();
            Details[] detail = new Details[n];
            int[][] arr = new int[n][m];
            for(int i = 0; i < n; i++){
                long sum = 0;
                for(int j = 0; j < m; j++) {
                    arr[i][j] = fr.nextInt();
                    sum += arr[i][j];
                }
                detail[i] = new Details(sum, i);
            }
            Arrays.sort(detail, (a, b) -> Long.compare(a.sum, b.sum));
            long sum = 0;
            long weight = n * m;
            for(int i = n - 1; i >= 0; i--){
                int row = detail[i].idx;
                for(int j = 0; j < m; j++){
                    sum += arr[row][j] * weight;
                    weight --;
                }
            }
            out.println(sum);
        }
        out.flush();
    }
}
class Details{
    long sum;
    int idx;
    Details(long sum, int idx){
        this.sum = sum;
        this.idx = idx;
    }
}