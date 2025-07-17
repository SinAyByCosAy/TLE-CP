package DPBootcamp.Graphs;

import java.util.*;

public class MSTPrims {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Pair>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();
            adj[x].add(new Pair(y, w));
            adj[y].add(new Pair(x, w));
        }
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long sum = 0;
        pq.add(new Node(1, 0));
        while(!pq.isEmpty()){
            Node top = pq.poll();
            if(visited[top.node]) continue;//already visited this node
            sum += top.weight;//we accept the node and it's weight
            visited[top.node] = true;
            for(Pair neighbor : adj[top.node]){//going over it's neighbors
                if(!visited[neighbor.x]){
                    pq.add(new Node(neighbor.x, neighbor.y));
                }
            }
        }
        System.out.println(sum);
    }
}
class Node implements Comparable<Node>{
    int node;
    long weight;
    Node(int node, long weight){
        this.node = node;
        this.weight = weight;
    }
    public int compareTo(Node obj){//ordering based on weight
        return Long.compare(this.weight, obj.weight);
    }
}
//TC: O(E . logV)