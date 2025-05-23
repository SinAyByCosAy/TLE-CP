package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//farthest end point from a node will always be one of the ends of the diameter
//take root, find farthest node : end1
//take end1, find farthest node : end2
//end1 and end2 are the two ends of the diameter
public class Diameter {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int parent = sc.nextInt();
            int child = sc.nextInt();
            adj[parent].add(child);
            adj[child].add(parent);
        }
        int[] distances = new int[n + 1];

        dfs(1, -1, 0, adj, distances);
        int end1 = 1, end2 = 1;
        for(int i = 1; i <= n; i++) {
            if(distances[end1] < distances[i]) end1 = i;
        }
        dfs(end1, -1, 0, adj, distances);
        for(int i = 1; i <= n; i++){
            if(distances[end2] < distances[i]) end2 = i;
        }
        System.out.println("Diameter ends: "+end1+", "+end2+" and len = "+(distances[end2] + 1));
    }
    public static void dfs(int node, int parent, int dist, List<Integer>[] adj, int[] distances){
        distances[node] = dist;
        for(int child : adj[node]){
            if(child != parent) dfs(child, node, dist + 1, adj, distances);
        }
    }
}
