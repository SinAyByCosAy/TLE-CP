//https://cses.fi/alon/task/1135
package DPBootcamp.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DistanceQueries {
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
        int q = fr.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int a = fr.nextInt();
            int b = fr.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        int[][] parentsList = new int[n + 1][21];
        int[] depth = new int[n + 1];
        precomputeParents(adj, parentsList, depth, 1, 0, 0);
        for(int i = 1; i <= q; i++){
            int a = fr.nextInt();
            int b = fr.nextInt();
            int x = lca(parentsList, depth, a, b);
            out.println(depth[a] + depth[b] - 2 * depth[x]);
        }
        out.flush();
    }
    public static void precomputeParents(List<Integer>[] adj, int[][] parentsList, int[] depth, int node, int parent, int currDepth){
        depth[node] = currDepth;
        parentsList[node][0] = parent;
        for(int i = 1; i <= 20; i++) parentsList[node][i] = parentsList[parentsList[node][i - 1]][i - 1];
        for(int child : adj[node]) if(child != parent) precomputeParents(adj, parentsList, depth, child, node, currDepth + 1);
    }
    public static int lca(int[][] parentsList, int[] depth, int a, int b){
        if(depth[b] > depth[a]){
            int t = a;
            a = b;
            b = t;
        }
        int diff = depth[a] - depth[b];
        int newA = findKthParent(parentsList, a, diff);
        if(newA == b) return newA;
        return getLCA(parentsList, newA, b);
    }
    public static int findKthParent(int[][] parentsList, int x, int y){
        for(int i = 0; i <= 20; i++){
            if((y & (1 << i)) > 0) x = parentsList[x][i];
        }
        return x;
    }
    public static int getLCA(int[][] parentsList, int a, int b){
        for(int i = 20; i >= 0; i--){
            if(parentsList[a][i] != parentsList[b][i]){
                a = parentsList[a][i];
                b = parentsList[b][i];
            }
        }
        return parentsList[a][0];
    }
}
//TC: O((Q+N)LogN)