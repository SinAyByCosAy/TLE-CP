//https://cses.fi/problemset/task/1732/
package DPBootcamp.StringHashing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FindingBorders {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
        Hash a = new Hash(s); //O(N)
        for(int i = 1; i < n; i++){
            Hashes prefix = a.get(0, i - 1); //O(1)
            Hashes suffix = a.get(n - i, n - 1);
            if(prefix.hash1 == suffix.hash1 && prefix.hash2 == suffix.hash2) out.print(i+" ");
        }
        out.flush();
    }
}
//TC: O(N)