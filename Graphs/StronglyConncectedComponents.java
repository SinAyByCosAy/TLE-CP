package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StronglyConncectedComponents {
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
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++){//getting the topo sort order
            if(!visited[i]) topo(i, adj, visited, order);
        }
        Collections.reverse(order);

        List<Integer>[] newEdges = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) newEdges[i] = new ArrayList<>();
        for(int i = 1; i <= n; i++){//reversing the edges
            visited[i] = false;//to use later
            for(int neighbor : adj[i]) newEdges[neighbor].add(i);
        }

        int countSCC = 0;
        for(int i = 0; i < n; i++){//traversing on topo sorted order
            if(!visited[order.get(i)]){
                countSCC++;
                dfs(order.get(i), newEdges, visited);
            }
        }
        System.out.println("Number of SCCs : " + countSCC);
    }
    public static void topo(int node, List<Integer>[] adj, boolean[] visited, List<Integer> order){
        visited[node] = true;
        for(int neighbor : adj[node]){
            if(!visited[neighbor]) topo(neighbor, adj, visited, order);
        }
        order.add(node);
    }
    public static void dfs(int node, List<Integer>[] newEdges, boolean[] visited){
        visited[node] = true;
        for(int neighbor : newEdges[node])
            if(!visited[neighbor]) dfs(neighbor, newEdges, visited);
    }
}
//TC: O(N + M)