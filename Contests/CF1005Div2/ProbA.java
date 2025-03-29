package DPBootcamp.Contests.CF1005Div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ProbA {
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
            String s = fr.nextLine();
            boolean zero = false;
            int prevBit = s.charAt(n - 1) - '0';
            if(prevBit == 0) zero = true;

            int count = 0;
            for(int i = n - 1; i >= 0; i--){
                char ch = s.charAt(i);
                int bit = ch - '0';
                if(bit == 1 && i == 0){
                    if(zero) count += 2;
                    else count += 1;
                }
                else if(bit == 1) prevBit = 1;
                else{
                    if(prevBit == 1) {
                        if (zero) count += 2;
                        else count += 1;
                    }
                    zero = true;
                    prevBit = 0;
                }
            }
            System.out.println(count);
        }
    }
}
