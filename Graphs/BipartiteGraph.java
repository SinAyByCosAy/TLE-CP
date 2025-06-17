package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//check if current graph is a bipartite graph or not?(does it contain odd length cycle or not?)
public class BipartiteGraph {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //number of nodes
        int m = sc.nextInt(); //number of edges
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        //any node can be in either of three colors: 0-uncolored-unvisited, 1, 2
        //value 0 tells us unvisited, hence we don't need the unvisited array
        int[] color = new int[n + 1];
        boolean isPossible = true;
        for(int i = 1; (i <= n) && isPossible; i++){//for a graph with multiple components
            if(color[i] == 0) isPossible = dfs(adj, color, i, 1);
        }
        System.out.println(isPossible);
    }
    public static boolean dfs(List<Integer>[] adj, int[] color, int node, int prevColor){
        int currColor = prevColor == 1 ? 2 : 1; //alternate color
        color[node] = currColor; //setting current color
        boolean isPossible = true;
        for(int neighbor : adj[node]){
            if(color[neighbor] == 0) //this is an unvisited neighbor
                isPossible = dfs(adj, color, neighbor, currColor);
            else if(color[neighbor] == currColor) isPossible = false; //we found invalid coloring
            if(!isPossible) return isPossible; //exit with false as soon as we find it
        }
        return isPossible;
    }
}
