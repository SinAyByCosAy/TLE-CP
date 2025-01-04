//https://cses.fi/problemset/task/2183/
package DPBootcamp.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MissingCoinSum {
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
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = fr.nextInt();
        long sum = 0;
        Arrays.sort(arr);
        for(int i = 0; i < n; i++){
            if(arr[i] <= sum + 1) sum += arr[i];
            else break;
        }
        out.println(sum + 1);
        out.flush();
    }
}
//TC: O(NlogN)