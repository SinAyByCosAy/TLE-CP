//https://cses.fi/problemset/task/1632/
package DPBootcamp.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MovieFestivalII {
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
    }
    public static void main(String[] args){
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        int k = fr.nextInt();
        MovieDetails[] timing = new MovieDetails[n];
        for(int i = 0; i < n; i++)
            timing[i] = new MovieDetails(fr.nextLong(), fr.nextLong());
        Arrays.sort(timing, (a, b) -> Long.compare(a.end, b.end));
        out.println(getUniqueMovies(timing, k));
        out.flush();
    }
    public static int getUniqueMovies(MovieDetails[] timing, int k){
        TreeMap<Long, Integer> map = new TreeMap<>();
        int concurrentViewers = 0;
        int count = 0;
        for(int i = 0; i < timing.length; i++){
            long start = timing[i].start;
            long end = timing[i].end;
            Long removeKey = map.floorKey(start);
            if(removeKey == null && concurrentViewers < k){
                map.put(end, 1);
                concurrentViewers++;
                count++;
            }else if(removeKey != null){
                map.put(removeKey, map.get(removeKey) - 1);
                if(map.get(removeKey) == 0) map.remove(removeKey);
                map.put(end, map.getOrDefault(end, 0) + 1);
                count++;
            }
        }
        return count;
    }
}
class MovieDetails{
    long start, end;
    MovieDetails(long start, long end){
        this.start = start;
        this.end = end;
    }
}
