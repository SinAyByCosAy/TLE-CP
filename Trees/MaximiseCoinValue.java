package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//sample problem to understand DP on trees
//given a tree of n nodes, where each node has ci coins,
// we need to choose a subset of nodes s.t. every pair of nodes in this subset is not connected by an edge.
// Find the max coin value we can get.
public class MaximiseCoinValue {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        int[] weights = new int[n + 1];
        for(int i = 1; i <= n; i++) weights[i] = sc.nextInt();
        int[][] dp = new int[n + 1][2];
        dfs(adj, dp, weights, 1, 0);
        System.out.println(Math.max(dp[1][1], dp[1][0]));
    }
    public static void dfs(List<Integer>[] adj, int[][] dp, int[] weights, int node, int parent){
        dp[node][1] = weights[node];
        for(int child : adj[node]){
            if(child != parent){
                dfs(adj, dp, weights, child, node);
                dp[node][1] += dp[child][0];
                dp[node][0] += Math.max(dp[child][1], dp[child][0]);
            }
        }
    }
}
