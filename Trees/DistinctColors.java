//https://cses.fi/problemset/task/1139/
package DPBootcamp.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class DistinctColors {
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
        HashSet<Integer>[] stColors = new HashSet[n + 1];
        for(int i = 1; i <= n; i++) stColors[i] = new HashSet<>();
        for(int i = 1; i <= n; i++) stColors[i].add(fr.nextInt());
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int a = fr.nextInt();
            int b = fr.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        int[] stSize = new int[n + 1];//for storing answers
        dfs(adj, stColors, 1, 0, stSize);
        for(int i = 1; i <= n; i++) out.print(stSize[i]+ " ");
        out.flush();
    }
    //every node gets merged log N times to reach the top node, and every insert in set takes logN time
    public static void dfs(List<Integer>[] adj, HashSet<Integer>[] stColors, int node, int parent, int[] stSize){
        for(int child : adj[node]){
            if(child != parent){
                dfs(adj, stColors, child, node, stSize);
                if(stColors[child].size() > stColors[node].size()) swap(stColors, child, node);
                stColors[node].addAll(stColors[child]);
            }
        }
        stSize[node] = stColors[node].size();
    }
    public static void swap(HashSet<Integer>[] hs, int a, int b){
        HashSet<Integer> t = hs[a];
        hs[a] = hs[b];
        hs[b] = t;
    }
}
//TC: O(N.log^2N)