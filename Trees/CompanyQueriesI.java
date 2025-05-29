//https://cses.fi/problemset/task/1687/
package DPBootcamp.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CompanyQueriesI {
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
    //find k-th parent of a node using Binary lifting in O(log N) time
    public static void main(String[] args){
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        int q = fr.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for(int parent = 2; parent <= n; parent++){
            int child = fr.nextInt();
            adj[parent].add(child);
            adj[child].add(parent);
        }
        int powerLimit = 20;//log(2e5) ~ 18
        int[][] parentsList = new int[n + 1][powerLimit];
        //pre computing 2^bit(1, 2, 4, 8) parents for each node
        precomputeParents(1, 0, adj, parentsList, powerLimit); //O(NlogN)
        for(int i = 1; i <= q; i++){//O(QlogN)
            //find y-th parent of x
            int x = fr.nextInt();
            int y = fr.nextInt();
            int res = getKthParent(parentsList, x, y, powerLimit);
            out.println(res == 0 ? -1 : res);
        }
        out.flush();
    }
    public static void precomputeParents(int node, int parent, List<Integer>[] adj, int[][] parentsList, int powerLimit){
        parentsList[node][0] = parent;
        for(int i = 1; i < powerLimit; i++) parentsList[node][i] = parentsList[parentsList[node][i - 1]][i - 1];
        for(int child : adj[node])
            if(child != parent) precomputeParents(child, node, adj, parentsList, powerLimit);
    }
    public static int getKthParent(int[][] parentsList, int x, int y, int powerLimit){
        int curr = x;
        for(int i = 0; i < powerLimit; i++){
            if((y & (1 << i)) > 0) curr = parentsList[curr][i];
        }
        return curr;
    }
}
//TC: O((N + Q) . logN)