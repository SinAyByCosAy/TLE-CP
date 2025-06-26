package DPBootcamp.Graphs;

import java.util.*;

//Given a complete graph, position of source, destination and enemy.
// Find if it's possible to reach from source to destination without enemy blocking.
public class EnemyInPath {
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
        int source = sc.nextInt();
        int dest = sc.nextInt();
        int enemy = sc.nextInt();
        if(source == enemy || dest == enemy){
            System.out.println("NO");
            return;
        }
        long[] enemyTime = new long[n + 1];
        Arrays.fill(enemyTime, Long.MAX_VALUE);
        TreeSet<Pair2> pq = new TreeSet<>();
        pq.add(new Pair2(enemy, 0));
        enemyTime[enemy] = 0;
        while(!pq.isEmpty()){//Dijkstra for enemy node
            Pair2 top = pq.removeFirst();
            for(Pair2 neighbor : adj[top.node]){
                if(top.dist + neighbor.dist < enemyTime[neighbor.node]){
                    pq.remove(new Pair2(neighbor.node, enemyTime[neighbor.node]));
                    enemyTime[neighbor.node] = top.dist + neighbor.dist;
                    pq.add(new Pair2(neighbor.node, enemyTime[neighbor.node]));
                }
            }
        }
        long[] sourceTime = new long[n + 1];
        Arrays.fill(sourceTime, Long.MAX_VALUE);
        pq.add(new Pair2(source, 0));
        sourceTime[source] = 0;
        while(!pq.isEmpty()){//Dijkstra for source node
            Pair2 top = pq.removeFirst();
            for(Pair2 neighbor : adj[top.node]){
                long time = top.dist + neighbor.dist;
                if(time < sourceTime[neighbor.node] && time < enemyTime[neighbor.node]){
                    pq.remove(new Pair2(neighbor.node, sourceTime[neighbor.node]));
                    sourceTime[neighbor.node] = time;
                    pq.add(new Pair2(neighbor.node, time));
                }
            }
        }
        if(sourceTime[dest] != Long.MAX_VALUE) System.out.println("YES");
        else System.out.println("NO");
    }
}
