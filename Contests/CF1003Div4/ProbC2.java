//https://codeforces.com/contest/2065/problem/C2
package DPBootcamp.Contests.CF1003Div4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProbC2 {
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
            int[] a = new int[n];
            Integer[] b = new Integer[m];
            for(int i = 0; i < n; i++){
                a[i] = fr.nextInt();
            }
            for(int i = 0; i < m; i++){
                b[i] = fr.nextInt();
            }
            Arrays.sort(b);
            int prev = Integer.MIN_VALUE;
            boolean pos = true;
            for(int i = 0; i < n; i++){
                int op = getLowerBound(b, m, prev + a[i]);
                if(a[i] >= prev){
                    prev = Math.min(a[i], op - a[i]);
                }else if(a[i] < prev && op != Integer.MAX_VALUE) prev = op - a[i];
                else{
                    pos = false;
                    break;
                }
            }
            if(pos) out.println("YES");
            else out.println("NO");
        }
        out.flush();
    }
    public static int getLowerBound(Integer[] b, int m, int x){
        int start = 0, end = m - 1;
        int ans = Integer.MAX_VALUE;
        while(start <= end){
            int mid = (start + end) / 2;
            if(b[mid] >= x){
                ans = b[mid];
                end = mid - 1;
            }else start = mid + 1;
        }
        return ans;
    }
}

