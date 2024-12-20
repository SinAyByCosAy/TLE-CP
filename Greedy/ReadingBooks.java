//https://cses.fi/problemset/task/1631/
package DPBootcamp.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ReadingBooks {
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
        long[] time = new long[n];
        for(int i = 0; i < n; i++)
            time[i] = fr.nextLong();
        Arrays.sort(time);

        long sum = 0;
        for(int i = 0; i < n - 1; i++)
            sum += time[i];

        if(sum >= time[n - 1]) sum += time[n - 1]; //by the time person 1 finished the first n-1 books, person 2 finished the last book, there is no overlap
        else sum = 2 * time[n - 1];  //sum = sum + (time[n-1] - sum) + time[n-1], there is overlap and we have to wait
        out.println(sum);
        out.flush();
    }
}
