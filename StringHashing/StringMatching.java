package DPBootcamp.StringHashing;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        PrintWriter out = new PrintWriter(System.out);
        String s = fr.nextLine();
        int n = s.length();
        String pattern = fr.nextLine();
        int m = pattern.length();
        Hashes[] hashes = new Hashes[n + 1];
        hashes[0] = new Hashes(0, 0);
        Bases[] bases = new Bases[n + 1];
        bases[0] = new Bases(1, 1);
        StringHashing.computeHashes(s, hashes, bases);

    }
}
