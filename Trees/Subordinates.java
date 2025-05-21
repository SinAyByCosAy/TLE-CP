//https://cses.fi/problemset/task/1674/
package DPBootcamp.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Subordinates {
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
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 2; i <= n; i++){
            int parent = fr.nextInt();
            adj[parent].add(i);
        }
        int[] sub = new int[n + 1];
        countSub(1, adj, sub);
        for(int i = 1; i <= n; i++) out.print(sub[i] - 1 + " ");
        out.println();
        out.flush();
    }
    public static void countSub(int node, List<Integer>[] adj, int[] sub){
        sub[node] = 1;
        for(int ele : adj[node]) {
            countSub(ele, adj, sub);
            sub[node] += sub[ele];
        }
    }
}
//TC: O(N)