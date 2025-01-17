//https://cses.fi/problemset/task/1733/
package DPBootcamp.StringHashing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FindingPeriods {
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
        String s = fr.nextLine();
        int n = s.length();
        Hash a = new Hash(s);
        for(int i = 1; i <= n; i++) {
            if(getPeriods(n, a, i)) out.print(i + " ");
        }
        out.flush();
    }
    public static boolean getPeriods(int n, Hash a, int size){
        boolean match = true;
        int window = size;
        for(int i = 0; i < n; i += size){
            window = Math.min(window, n - i); //making sure the last window is of apt length
            Hashes pattern = a.get(0, window - 1);
            Hashes check = a.get(i, i + window - 1);
            if(pattern.hash1 != check.hash1 || pattern.hash2 != check.hash2){
                match = false;
                break;
            }
        }
        return match;
    }
}
//TC: O(NlogN)