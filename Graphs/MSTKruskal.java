package DPBootcamp.Graphs;

import java.util.*;
//Minimum spanning tree using Kruskal's algo
public class MSTKruskal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Edges[] edges = new Edges[m];
        for(int i = 0; i < m; i++) edges[i] = new Edges(sc.nextInt(), sc.nextInt(), sc.nextInt());
        Arrays.sort(edges, (a, b) -> Integer.compare(a.w, b.w)); //O(MlogM)

        long sum = 0;
        DSU3.init(n);
        for(int i = 0; i < m; i++){
            int u = edges[i].u;
            int v = edges[i].v;
            int w = edges[i].w;
            if(!DSU3.isSameSet(u, v)){//we need to make a connection
                DSU3.unionSet(u, v);
                sum += w; //we have the best possible weight to pick right now
            }
        }
        //if we got a disconnected graph in input itself, we won't be able to produce a solution
        //don't need this if we are sure we'll get a complete graph
        for(int i = 1; i <= n; i++){
            if(!DSU3.isSameSet(i, i + 1)){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sum);
    }
}
class DSU3{
    static int[] parent, rank;
    public static void init(int n){
        parent = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 1; i <= n; i++) parent[i] = i;
    }
    public static int findSet(int v){
        if(parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }
    public static boolean isSameSet(int x, int y){
        return findSet(x) == findSet(y);
    }
    public static void unionSet(int x, int y){
        int a = findSet(x);
        int b = findSet(y);
        if(rank[a] < rank[b]){ int t = a; a = b; b = t; }
        parent[b] = a;
        if(rank[a] != rank[b]) rank[a]++;
    }
}
class Edges{
    int u, v, w;
    Edges(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
//TC: O(M . logM)