package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LongestDistanceLCA {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int parent = sc.nextInt();
            int child = sc.nextInt();
            adj[parent].add(child);
        }
        int[] height = new int[n + 1];
        int[] maxLCALength = new int[n + 1];
        findLongestLCA(1, adj, height, maxLCALength);
        int diameter = 0;
        for(int i = 1; i <= n; i++) {
            diameter = Math.max(diameter, maxLCALength[i]);//max LCA is the diameter
            System.out.println("Node: " + i + ", having longest dist as LCA = " + maxLCALength[i]);
        }
        System.out.println("diameter: " + diameter);
    }
    public static void findLongestLCA(int node, List<Integer>[] adj, int[] height, int[] maxLCALength){
        height[node] = 1;
        int maxHeight = 0, secondMaxHeight = 0;
        for(int child : adj[node]){
            findLongestLCA(child, adj, height, maxLCALength);

            height[node] = Math.max(height[node], height[child] + 1);//maintaining the height of each node
            //need max and second max heights of a node for LCA
            if(height[child] >= maxHeight){
                secondMaxHeight = maxHeight;
                maxHeight = height[child];
            }else if(height[child] > secondMaxHeight) secondMaxHeight = height[child];
        }
        //LCA
        maxLCALength[node] = maxHeight + secondMaxHeight + 1;
    }
}
