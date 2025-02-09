package DPBootcamp.Contests.CF1003Div4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ProbC1 {
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
            for(int i = 0; i < n; i++){
                a[i] = fr.nextInt();
            }
            int k = fr.nextInt();
            boolean pos = true;
            a[0] = Math.min(a[0], k - a[0]);
            for(int i = 1; i < n; i++){
                if(a[i] >= a[i - 1] && k - a[i] >= a[i - 1]){
                    a[i] = Math.min(a[i], k - a[i]);
                }else if(a[i] >= a[i - 1]) continue;
                else if(k - a[i] >=a[i - 1]) a[i] = k - a[i];
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
}

