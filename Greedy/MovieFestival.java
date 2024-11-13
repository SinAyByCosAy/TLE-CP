//https://cses.fi/problemset/task/1629/
package DPBootcamp.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MovieFestival {
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

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = fr.nextInt();  // Single integer
        Pair[] interval = new Pair[n];
        for(int i = 0; i < n; i++) {
            Pair pair = new Pair(fr.nextInt(), fr.nextInt());
            interval[i] = pair;
        }
        Arrays.sort(interval, (a, b) -> Integer.compare(a.end, b.end));

        int count = 0;
        int endTime = -1;
        for(int i = 0; i < n; i++){
            if(interval[i].start >= endTime){
                count++;
                endTime = interval[i].end;
            }
        }

        out.println(count);
        // Always flush the output
        out.flush();
    }
}
class Pair{
    int start, end;
    Pair(int start, int end){
        this.start = start;
        this.end = end;
    }
}