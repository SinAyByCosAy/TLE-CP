//https://cses.fi/problemset/task/1753
package DPBootcamp.StringHashing;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StringMatching {
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
        String s = fr.nextLine();
        String pattern = fr.nextLine();
        int n = s.length();
        int m = pattern.length();

        Hash a = new Hash(s); //pre-compute hashes, from StringHashingTemplate
        Hash b = new Hash(pattern);
        Hashes patternHash = b.get(0, m - 1);
        int count = 0;
        for(int i = 0; i < n - (m - 1); i++){
            Hashes sub = a.get(i, i + (m - 1));
            if(sub.hash1 == patternHash.hash1 && sub.hash2 == patternHash.hash2) count++;
        }
        System.out.println(count);
    }
}
//TC:O(N + M)