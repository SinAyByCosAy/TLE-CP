//https://codeforces.com/edu/course/2/lesson/7/2/practice/contest/289391/problem/A
package DPBootcamp.Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PeopleAreLeaving {
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
        int m = fr.nextInt();
        DSU4.init(n);
        for(int i = 1; i <= m; i++){
            String op = fr.next();
            int val = fr.nextInt();
            if(op.equals("?")){
                int res = DSU4.findRight(val);
                out.println(res == (n + 1) ? -1 : res);
            }
            else DSU4.unionSet(val, val + 1);
        }
        out.flush();
    }
}
class DSU4{
    static int[] parent, rank, right;
    public static void init(int n){
        parent = new int[n + 2];
        rank = new int[n + 2];
        right = new int[n + 2];
        for(int i = 1; i <= n + 1; i++){
            parent[i] = i;
            right[i] = i;
        }
    }
    public static int findSet(int v){
        if(parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }
    public static void unionSet(int x, int y){
        int a = findSet(x);
        int b = findSet(y);
        if(rank[a] < rank[b]){ int t = a; a = b; b = t; }
        parent[b] = a;
        if(rank[a] == rank[b]) rank[a]++;
        //since 'a' is the root after the union, we store right in 'a'
        right[a] = Math.max(right[a], right[b]);
    }
    public static int findRight(int v){
        return right[findSet(v)];
    }
}
//TC: O(N + M)