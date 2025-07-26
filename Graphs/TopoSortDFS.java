package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//Topological sort in DAG using DFS
public class TopoSortDFS {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
        }
        boolean[] visited = new boolean[n + 1];
        List<Integer> ans = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(!visited[i]) dfs(i, adj, visited, ans);
        }
        Collections.reverse(ans);
        System.out.println(ans);
    }
    public static void dfs(int node, List<Integer>[] adj, boolean[] visited, List<Integer> ans){
        visited[node] = true;
        for(int neighbor : adj[node]){
            if(!visited[neighbor]) dfs(neighbor, adj, visited, ans);
        }
        ans.add(node);
    }
}
