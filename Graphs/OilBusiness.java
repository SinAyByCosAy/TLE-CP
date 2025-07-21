//https://codeforces.com/edu/course/2/lesson/7/2/practice/contest/289391/problem/H
package DPBootcamp.Graphs;

import java.util.*;

//We need to remove max number of edges w/o making the graph disconnected.
// Smaller the weight of the edges, the more we can remove.
//Therefore, we find Maximum spanning tree(used Prims here), then take out the unvisited edges.
//Sort the unvisited edges. Grab all the edges s.t. Sum(weight) <= k
public class OilBusiness {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long k = sc.nextLong();
        List<Edge2>[] adj = new ArrayList[n + 1];
        long[] edgeWeight = new long[m + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            long w = sc.nextLong();
            adj[x].add(new Edge2(y, w, i));
            adj[y].add(new Edge2(x, w, i));
            edgeWeight[i] = w;
        }
        boolean[] visited = new boolean[n + 1];
        boolean[] visitedEdge = new boolean[m + 1];
        PriorityQueue<Edge2> pq = new PriorityQueue<>(Collections.reverseOrder());//max heap
        pq.add(new Edge2(1, 0, 0));
        while(!pq.isEmpty()){
            Edge2 top = pq.poll();
            if(visited[top.node]) continue;
            visited[top.node] = true;
            visitedEdge[top.id] = true;
            for(Edge2 neighbor : adj[top.node]){
                pq.add(new Edge2(neighbor.node, neighbor.weight, neighbor.id));
            }
        }
        List<Node> remEdges = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            if(!visitedEdge[i]) remEdges.add(new Node(i, edgeWeight[i]));
        }
        Collections.sort(remEdges, (a, b) -> Long.compare(a.weight, b.weight));
        List<Integer> ans = getDeletionCount(remEdges, k);
        System.out.println(ans.size());
        for(int ele : ans) System.out.print(ele + " ");
        System.out.println();
    }
    public static List<Integer> getDeletionCount(List<Node> remEdges, long k){
        long sum = 0;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < remEdges.size(); i++){
            sum += remEdges.get(i).weight;
            if(sum > k) break;
            ans.add(remEdges.get(i).node);
        }
        return ans;
    }
}
class Edge2 implements Comparable<Edge2>{
    int node, id;
    long weight ;
    Edge2(int node, long weight, int id){
        this.node = node;
        this.weight = weight;
        this.id = id;
    }
    public int compareTo(Edge2 obj){//why can't this be static?
        return Long.compare(this.weight, obj.weight);
    }
}