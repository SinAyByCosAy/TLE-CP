//https://cses.fi/problemset/task/1671/
package DPBootcamp.Graphs;

import java.util.*;

public class Dijkstra {
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
            adj[x].add(new Pair2(y, z)); //x: y and z, z is distance
        }
        for(int i = 1; i <= n; i++){
            System.out.print(i + " : ");
            for(int j = 0; j < adj[i].size(); j++) System.out.print("("+adj[i].get(j).node + ","+adj[i].get(j).dist+") ");
            System.out.println();
        }
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;
        TreeSet<Pair2> pq = new TreeSet<>();
        pq.add(new Pair2(1, 0)); //first node

        while(!pq.isEmpty()){
            Pair2 top = pq.removeFirst();//least costly node, it's value is now fixed.
            for(Pair2 neighbors : adj[top.node]){
                long sum = top.dist + neighbors.dist;
                if(sum < dist[neighbors.node]){//found a path with better value to this node
                    pq.remove(new Pair2(neighbors.node, dist[neighbors.node]));
                    pq.add(new Pair2(neighbors.node, sum)); //update the node
                    dist[neighbors.node] = sum;
                }
            }
        }
        for(int i = 1; i <= n; i++) System.out.print(dist[i] + " ");
    }
}
class Pair2 implements Comparable<Pair2> {
    int node;
    long dist;

    Pair2(int node, long dist) {
        this.node = node;
        this.dist = dist;
    }

    @Override
    public int compareTo(Pair2 other) {
        // First, compare by distance
        if (this.dist != other.dist) {
            return Long.compare(this.dist, other.dist);
        }
        // If distances are equal, use the node number as a tie-breaker
        return Integer.compare(this.node, other.node);
    }
}
//TC: O(E.logV)