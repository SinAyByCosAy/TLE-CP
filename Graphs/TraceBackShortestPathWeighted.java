package DPBootcamp.Graphs;

import java.util.*;
//given a graph, find all the nodes that lie on the shortest point between a source and destination
public class TraceBackShortestPathWeighted {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Pair3>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            //here we want to be able to move in any direction, to and fro
            adj[x].add(new Pair3(y, z));
            adj[y].add(new Pair3(x, z));
        }
        int source = sc.nextInt();
        int dest = sc.nextInt();
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        TreeSet<Pair3> pq = new TreeSet<>();
        pq.add(new Pair3(source, 0));
        dist[source] = 0;
        while(!pq.isEmpty()){
            Pair3 top = pq.removeFirst();
            for(Pair3 neighbor : adj[top.node]){
                if(top.dist + neighbor.dist < dist[neighbor.node]){
                    pq.remove(new Pair3(neighbor.node, dist[neighbor.node])); //dist[neighbor.node] holds the current dist of the node
                    dist[neighbor.node] = top.dist + neighbor.dist;
                    pq.add(new Pair3(neighbor.node, dist[neighbor.node]));
                    parent[neighbor.node] = top.node; //storing parent info
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        int idx = dest;
        while(parent[idx] != -1){//backtracking path
            path.add(parent[idx]);
            idx = parent[idx];
        }
        Collections.reverse(path);
        System.out.println(path);
    }
}
class Pair3 implements Comparable<Pair3>{
    int node;
    long dist;
    Pair3(int node, long dist){
        this.node = node;
        this.dist = dist;
    }
    public int compareTo(Pair3 obj){
        if(this.dist != obj.dist)
            return Long.compare(this.dist, obj.dist);
        return Integer.compare(this.node, obj.node);
    }
}
//TC: O(E.logV)