//https://cses.fi/problemset/task/1686/
package DPBootcamp.Graphs;

import java.util.*;

//We are looking at strongly connected components
public class CoinCollector {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] coins = new int[n + 1];
        for(int i = 1; i <= n; i++) coins[i] = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        List<Integer>[] revAdj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){adj[i] = new ArrayList<>(); revAdj[i] = new ArrayList<>();}
        for(int i = 1; i <= m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            revAdj[y].add(x);//creating reverse adjacency list at input itself
        }
        //Topo sort, needed to know the order of nodes for correct traversal
        boolean[] visited = new boolean[n + 1];
        List<Integer> order = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(!visited[i]) topo(i, adj, visited, order);
        }
        Collections.reverse(order);

        //giving nodes a component id
        int[] color = new int[n + 1];
        int c = 1;
        for(int i = 0; i < n; i++){
            if(color[order.get(i)] == 0) getComponent(order.get(i), revAdj, c++, color);
            //revAdj is used to stay in the same component since edges are reversed
        }
        long[] sccCoins = new long[c];
        List<Integer>[] sccAdj = new ArrayList[c];
        for(int i = 1; i < c; i++) sccAdj[i] = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            sccCoins[color[i]] += coins[i]; //add weights of nodes in the same SCC
            for(int neighbor : adj[i]){
                if(color[i] != color[neighbor]) //checking for edge to another SCC
                    sccAdj[color[i]].add(color[neighbor]); //it's possible to have multiple edges to same node [1]:{2,2}
                    //but that's fine.
            }
        }
        long ans = 0;
        long[] dp = new long[c];
        Arrays.fill(dp, -1);
        //considering each node as the source node to get the max answer
        for(int i = 1; i < c; i++) ans = Math.max(ans, dfs(i, sccAdj, sccCoins, dp));
        System.out.println(ans);
    }
    private static void getComponent(int node, List<Integer>[] revAdj, int id, int[] color){
        color[node] = id;
        for(int neighbor : revAdj[node]){
            if(color[neighbor] == 0) getComponent(neighbor, revAdj, id, color);
        }
    }
    private static void topo(int node, List<Integer>[] adj, boolean[] visited, List<Integer> order){
        visited[node] = true;
        for(int neighbor : adj[node]){
            if(!visited[neighbor]) topo(neighbor, adj, visited, order);
        }
        order.add(node);
    }
    private static long dfs(int node, List<Integer>[] sccAdj, long[] sccCoins, long[] dp){
        if(dp[node] != -1) return dp[node];
        dp[node] = sccCoins[node]; //base value, if no neighbors
        for(int neighbor : sccAdj[node]){
            dp[node] = Math.max(dp[node], sccCoins[node] + dfs(neighbor, sccAdj, sccCoins, dp));
        }
        return dp[node];
    }
}
//TC: O(N + M)