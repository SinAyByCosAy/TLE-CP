//https://codeforces.com/problemset/problem/1882/D
package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeXOR {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] weights = new int[n + 1];
            for(int i = 1; i <= n; i++) weights[i] = sc.nextInt();
            List<Integer>[] adj = new ArrayList[n + 1];
            for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for(int i = 1; i < n; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }
            int[] size = new int[n + 1];
            long[] dp = new long[n + 1];
            long ans = dfs(adj, size, weights, 1, 0);
            reroot(adj, size, weights, dp, 1, 0, ans);
            for(int i = 1; i <= n; i++) System.out.print(dp[i] + " ");
            System.out.println();
        }
    }
    public static long dfs(List<Integer>[] adj, int[] size, int[] weights, int node, int parent){
        size[node] = 1;
        long ans = 0;
        for(int child : adj[node]){
            if(child != parent){
                ans += dfs(adj, size, weights, child, node) + (weights[child] ^ weights[node]) * 1l * size[child];
                size[node] += size[child];
            }
        }
        return ans;
    }
    public static void reroot(List<Integer>[] adj, int[] size, int[] weights, long[] dp, int node, int parent, long ans){
        dp[node] = ans;
        for(int child : adj[node]){
            if(child != parent) reroot(adj, size, weights, dp, child, node, ans + (weights[child] ^ weights[node]) * 1l * (size[1] - 2 * size[child]));
        }
    }
}
//TC: O(N)