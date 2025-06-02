//https://cses.fi/problemset/task/1688/
package DPBootcamp.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CompanyQueriesII {
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
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 2; i <= n; i++){
            int par = fr.nextInt();
            adj[par].add(i);
            adj[i].add(par);
        }
        int powerLimit = 20;
        int[] depth = new int[n + 1];
        int[][] parentsList = new int[n + 1][powerLimit + 1];
        precomputeParents(adj, parentsList, 1, 0, 0, depth, powerLimit); //O(NLogN)

        for(int i = 1; i <= q; i++){//O(Q.logN)
            int a = fr.nextInt();
            int b = fr.nextInt();
            out.println(lca(a, b, parentsList, depth, powerLimit));
        }
        out.flush();
    }
    public static void precomputeParents(List<Integer>[] adj, int[][] parentsList, int node, int parent, int currDepth, int[] depth, int powerLimit){
        depth[node] = currDepth;//storing depths of each node
        parentsList[node][0] = parent;
        for(int i = 1; i <= powerLimit; i++) parentsList[node][i] = parentsList[parentsList[node][i - 1]][i - 1];
        for(int child : adj[node]){
            if(child != parent) precomputeParents(adj, parentsList, child, node, currDepth + 1, depth, powerLimit);
        }
    }//O(NlogN)

    public static int lca(int a, int b, int[][] parentsList, int[] depth, int powerLimit){
        if(depth[b] > depth[a]) {//let's keep node a as the lower one for any case
            int t = a;
            a = b;
            b = t;
        }
        int diff = depth[a] - depth[b];
        int newA = findKthParent(a, diff, parentsList, powerLimit); //O(logN)
        if(newA == b) return newA;
        return getLCA(newA, b, parentsList, powerLimit); //O(logN)
    }
    public static int findKthParent(int x, int y, int[][] parentsList, int powerLimit){
        for(int i = 0; i <= powerLimit; i++){
            if((y & (1 << i)) > 0) x = parentsList[x][i];
        }
        return x;
    }
    public static int getLCA(int a, int b, int[][] parentsList, int powerLimit){
        for(int i = powerLimit; i >= 0; i--){
            if(parentsList[a][i] != parentsList[b][i]){
                a = parentsList[a][i];
                b = parentsList[b][i];
            }
        }
        return parentsList[a][0];
    }
}
//TC: O((Q+N).logN)