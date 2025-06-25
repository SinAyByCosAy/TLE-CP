package DPBootcamp.Graphs;

import java.util.*;

//given a graph, find the shortest path using Dijkstra s.t. we can slash an edge's distance by 50%
public class ShortestPathWithHalving {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Pair2>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            adj[x].add(new Pair2(y, z));
            adj[y].add(new Pair2(x, z));
        }
        long[][] dist = new long[n + 1][2];
        for(long[] row : dist) Arrays.fill(row, Long.MAX_VALUE);
        TreeSet<Tuple> pq = new TreeSet<>();
        pq.add(new Tuple(1, 0, 0));//no halving in the first node
        dist[1][0] = 0;
        dist[1][1] = 0;
        while(!pq.isEmpty()){
            Tuple top = pq.removeFirst();
            for(Pair2 neighbor : adj[top.node]) {
                //when we don't half the weight, op can be 0 or 1
                if(top.dist + neighbor.dist < dist[neighbor.node][top.op]){
                    pq.remove(new Tuple(neighbor.node, dist[neighbor.node][top.op], top.op));
                    dist[neighbor.node][top.op] = top.dist + neighbor.dist;
                    pq.add(new Tuple(neighbor.node, dist[neighbor.node][top.op], top.op));
                }
                if(top.op == 0){//we can half the weight, op = 0
                    long weight = neighbor.dist / 2;
                    if(top.dist + weight < dist[neighbor.node][1]){
                        pq.remove(new Tuple(neighbor.node, dist[neighbor.node][1], 1));
                        dist[neighbor.node][1] = top.dist + weight;
                        pq.add(new Tuple(neighbor.node, dist[neighbor.node][1], 1));
                    }
                }
            }
        }
        for(int i = 1; i <= n; i++) System.out.println(i +" : "+dist[i][0]+" , "+dist[i][1]);
    }
}
class Tuple implements Comparable<Tuple>{
    int node, op;
    long dist;
    Tuple(int node, long dist, int op){
        this.node = node;
        this.dist = dist;
        this.op = op;
    }
    public int compareTo(Tuple obj){
        if(this.dist != obj.dist)
            return Long.compare(this.dist, obj.dist);
        return Integer.compare(this.node, obj.node);
    }
}
//TC: O(E.logV)