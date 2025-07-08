//https://codeforces.com/problemset/problem/1915/G
package DPBootcamp.Graphs;

import java.util.*;

public class Bicycles {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            List<Pair2>[] adj = new ArrayList[n + 1];
            for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for(int i = 1; i <= m; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                int w = sc.nextInt();
                adj[x].add(new Pair2(y, w));
                adj[y].add(new Pair2(x, w));
            }
            int[] cycles = new int[n + 1];
            for(int i = 1; i <= n; i++) cycles[i] = sc.nextInt();
            long[][] dist = new long[n + 1][1001];//stores the nodes distance via cycle = j
            for(long[] row : dist) Arrays.fill(row, Long.MAX_VALUE);
            TreeSet<Pair5> pq = new TreeSet<>();
            pq.add(new Pair5(1, 0, cycles[1]));
            dist[1][cycles[1]] = 0;
            while(!pq.isEmpty()){
                Pair5 top = pq.pollFirst();
                for(Pair2 neighbors : adj[top.node]){
                    long sum = top.dist + neighbors.dist * top.cycle;
                    if(sum < dist[neighbors.node][top.cycle]){//found a better distance to the node with same cycle
                        pq.remove(new Pair5(neighbors.node, dist[neighbors.node][top.cycle], top.cycle));//remove old dist
                        pq.add(new Pair5(neighbors.node, sum, Math.min(top.cycle, cycles[neighbors.node])));//add new dist
                        dist[neighbors.node][top.cycle] = sum;
                    }
                }
            }
            long ans = Long.MAX_VALUE;
            for(int i = 1; i <= 1000; i++) ans = Math.min(ans, dist[n][i]);
            System.out.println(ans);
        }
    }
}
class Pair5 implements Comparable<Pair5>{
    int node, cycle;
    long dist;
    Pair5(int node, long dist, int cycle){
        this.node = node;
        this.dist = dist;
        this.cycle = cycle;
    }
    public int compareTo(Pair5 obj){
        if(this.dist != obj.dist) return Long.compare(this.dist, obj.dist);
        if(this.node != obj.node) return Integer.compare(this.node, obj.node);
        return Integer.compare(this.cycle, obj.cycle);
    }
}
//TC: O((N^2 + E).logN)