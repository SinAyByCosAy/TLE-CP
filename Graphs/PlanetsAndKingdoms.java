//https://cses.fi/problemset/task/1683
package DPBootcamp.Graphs;

import java.util.*;

public class PlanetsAndKingdoms {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        List<Integer>[] revAdj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) { adj[i] = new ArrayList<>(); revAdj[i] = new ArrayList<>(); }
        for(int i = 1; i <= m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            revAdj[y].add(x);
        }
        boolean[] visited = new boolean[n + 1];
        List<Integer> order = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(!visited[i]) topo(i, adj, visited, order );
        }
        Collections.reverse(order);

        Arrays.fill(visited, false);
        int[] res = new int[n + 1];
        int countSCC = 0;
        for(int i = 0; i < n; i++){
            if(!visited[order.get(i)]){
                countSCC++;
                dfs(order.get(i), revAdj, visited, res, countSCC);
            }
        }
        System.out.println(countSCC);
        for(int i = 1; i <= n; i++) System.out.print(res[i] + " ");
        System.out.println();
    }
    public static void topo(int node, List<Integer>[] adj, boolean[] visited, List<Integer> order){
        visited[node] = true;
        for(int neighbor : adj[node])
            if(!visited[neighbor]) topo(neighbor, adj, visited, order);
        order.add(node);
    }
    public static void dfs(int node, List<Integer>[] revAdj, boolean[] visited, int[] res, int id){
        visited[node] = true;
        res[node] = id;
        for(int neighbor : revAdj[node]){
            if(!visited[neighbor]) dfs(neighbor, revAdj, visited, res, id);
        }
    }
}
