//https://cses.fi/alon/task/1133/
package DPBootcamp.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeDistanceII {
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
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int a = fr.nextInt();
            int b = fr.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        long[] dp = new long[n + 1];
        int[] size = new int[n + 1];
        long ans = dfs(adj, size, 1, 0, 0); //calculating total distance once for the root
        reroot(adj, dp, size, 1, 0, ans); //making every other node as root
        for(int i = 1; i <= n; i++) out.print(dp[i]+" ");
        out.flush();
    }
    public static long dfs(List<Integer>[] adj, int[] size, int node, int parent, int depth){
        size[node] = 1;//maintaining size
        long ans = depth; //maintaining distance from the root
        for(int child : adj[node]){
            if(child != parent){
                ans += dfs(adj, size, child, node, depth + 1);
                size[node] += size[child];
            }
        }
        return ans;
    }
    public static void reroot(List<Integer>[] adj, long[] dp, int[] size, int node, int parent, long ans){
        dp[node] = ans;
        for(int child : adj[node]){
            if(child != parent) reroot(adj, dp, size, child, node, dp[node] + size[1] - 2 * size[child]); //re-rooting calculation
        }
    }
}
//TC: O(N)