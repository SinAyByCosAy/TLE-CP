package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//given a (n * n) binary matrix, find the max and min areas of an island.
//0: water, 1: land
public class MaxMinAreaOnBinaryMatrix {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) for(int j = 1; j <= n; j++) matrix[i][j] = sc.nextInt();
        int limit = n * n + 1;
        List<Integer>[] adj = new ArrayList[limit];
        List<Integer> nodes = new ArrayList<>();
        for(int i = 1; i < limit; i++) adj[i] = new ArrayList<>();
        int idx = 1;
        for(int i = 1; i <= n; i++){//making the graph
            for(int j = 1; j <= n; j++){
                if(matrix[i][j] == 1){//found a valid node
                    nodes.add(idx);
                    //making edges with all it's neighbors
                    if(i - 1 >= 1 && matrix[i - 1][j] == 1) adj[idx].add(idx - n);
                    if(i + 1 <= n && matrix[i + 1][j] == 1) adj[idx].add(idx + n);
                    if(j - 1 >= 1 && matrix[i] [j - 1] == 1) adj[idx].add(idx - 1);
                    if(j + 1 <= n && matrix[i][j + 1] == 1) adj[idx].add(idx + 1);
                }
                idx++;
            }
        }
        int maxArea = 0;
        int minArea = n * n;
        boolean[] visited = new boolean[limit];
        for(int i = 0; i < nodes.size(); i++){//traversing over valid nodes
            if(!visited[nodes.get(i)]){//found a node of a component not yet explored
                int area = dfs(nodes.get(i), adj, visited);//explore it's graph
                maxArea = Math.max(maxArea, area);
                minArea = Math.min(minArea, area);
            }
        }
        System.out.println(maxArea);
        System.out.println(minArea);
    }
    public static int dfs(int node, List<Integer>[] adj, boolean[] visited){
        int count = 1;//count of nodes
        visited[node] = true;
        for(int neighbor : adj[node]) if(!visited[neighbor]) count += dfs(neighbor, adj, visited);
        return count;
    }
}
