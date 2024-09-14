//https://cses.fi/problemset/task/1081/
package DPBootcamp.NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CommonDivisors {
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
        PrintWriter out = new PrintWriter(System.out, true);
        int n = fr.nextInt();
        int limit = (int) 1e6 + 1;
        int[] freq = new int[limit];
        for(int i = 0; i < n; i++){//marking the input numbers
            freq[fr.nextInt()]++;
        }
        for(int i = limit - 1; i >= 1; i--){
            int count = 0;
            for(int j = i; j < limit; j += i)
                count += freq[j];
            if(count > 1){//if a number has 2 multiples marked, that's the answer`
                out.println(i);
                break;
            }
        }
    }
}
//TC: O(NlogN), sum of harmonic series