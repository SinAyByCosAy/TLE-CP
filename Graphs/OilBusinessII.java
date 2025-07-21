//https://codeforces.com/edu/course/2/lesson/7/2/practice/contest/289391/problem/H
package DPBootcamp.Graphs;

import java.util.*;

//Implementing the same logic using Kruskal's
public class OilBusinessII {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long k = sc.nextLong();
        Edges2[] edges = new Edges2[m];
        for(int i = 0; i < m; i++){
            edges[i] = new Edges2(sc.nextInt(), sc.nextInt(), sc.nextLong(), i + 1);
        }
        Arrays.sort(edges, (a, b) -> Long.compare(b.w, a.w));//descending order

        DSU5.init(n);
        List<Node> remEdge = new ArrayList<>();
        for(int i = 0; i < m; i++){
            int u = edges[i].u, v = edges[i].v, id = edges[i].id;
            long w = edges[i].w;
            if(!DSU5.isSameSet(u, v))//Max Spanning tree edge
                DSU5.unionSet(u, v);
            else//rejected edge needs to be tracked(stored in descending order)
                remEdge.add(new Node(id, w));
        }
        int count = 0;
        long sum = 0;
        int limit = remEdge.size();
        for(int i = limit - 1; i >= 0; i--){//collecting #edges to delete
            sum += remEdge.get(i).weight;
            if(sum > k) break;
            count++;
        }
        System.out.println(count);
        //Element from the end are the smallest
        for(int i = 0; i < count; i++) System.out.print(remEdge.get(limit - 1 - i).node + " ");
        System.out.println();
    }
}
class DSU5{
    static int[] parent, rank;
    public static void init(int n){
        parent = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 1; i <= n; i++) parent[i] = i;
    }
    public static boolean isSameSet(int x, int y){
        return findSet(x) == findSet(y);
    }
    public static int findSet(int v){
        if(parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }
    public static void unionSet(int x, int y){
        int a = findSet(x);
        int b = findSet(y);
        if(a != b) {
            if (rank[a] < rank[b]) { int t = a; a = b; b = t;}
            parent[b] = a;
            if (rank[a] == rank[b]) rank[a]++;
        }
    }
}
class Edges2{
    int u, v, id;
    long w;
    Edges2(int u, int v, long w, int id){
        this.u = u;
        this.v = v;
        this.w = w;
        this.id = id;
    }
}
//TC: O(N + MlogM)