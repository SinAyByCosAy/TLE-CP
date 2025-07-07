//https://cses.fi/problemset/task/1196
package DPBootcamp.Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class FlightRoutes {
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
        int k = fr.nextInt();
        List<Pair4>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            int x = fr.nextInt();
            int y = fr.nextInt();
            int w = fr.nextInt();
            adj[x].add(new Pair4(y, w));
        }
        TreeSet<Pair4> pq = new TreeSet<>(); //Global queue
        PriorityQueue<Long>[] dist = new PriorityQueue[n + 1]; //distance array, PQ for each node
        for(int i = 1; i <= n; i++) {
            dist[i] = new PriorityQueue<>(Collections.reverseOrder()); //max heap
            dist[i].add(Long.MAX_VALUE);
        }
        pq.add(new Pair4(1, 0)); //starting node
        dist[1].add(0l);
        while(!pq.isEmpty()){
            Pair4 top = pq.pollFirst();
            for(Pair4 neighbor : adj[top.node]){
                long sum = top.dist + neighbor.dist;
                if(dist[neighbor.node].size() < k){ //we accept any distance till we have k of them
                    pq.add(new Pair4(neighbor.node, sum));
                    dist[neighbor.node].add(sum);
                }else if(dist[neighbor.node].peek() > sum){//we already have k distances, also we have found a better distance
                    long val = dist[neighbor.node].poll(); //remove the highest distance
//                    pq.remove(new Pair4(neighbor.node, val));
                    pq.add(new Pair4(neighbor.node, sum)); //add new distance in global queue
                    dist[neighbor.node].add(sum);
                }
            }
        }
        List<Long> ans = new ArrayList<>();
        for(long val : dist[n]) if(val != Long.MAX_VALUE) ans.add(val);
        Collections.sort(ans);
        for(long val : ans) out.print(val + " ");
        out.flush();
    }
}
class Pair4 implements Comparable<Pair4>{
    int node;
    long dist;
    private static int counter = 0; //static counter gets share across all instances
    private final int id; //using id to differentiate between nodes, so that treeset can have duplicates
    Pair4(int node, long dist){
        this.node = node;
        this.dist = dist;
        this.id = counter++; //locking id value for current node
    }
    public int compareTo(Pair4 obj){
        if(this.dist != obj.dist)
            return Long.compare(this.dist, obj.dist);
        if(this.node != obj.node)
            return Integer.compare(this.node, obj.node);
        return Integer.compare(this.id, obj.id);
    }
}
//TC: O(E.logV)