package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//find minimum node by weight on the path a and b
public class MinInPath {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        int[] weight = new int[n + 1];
        for(int i = 2; i <= n; i++){
            int parent = sc.nextInt();
            adj[parent].add(i);
            adj[i].add(parent);
        }
        for(int i = 1; i <= n; i++) weight[i] = sc.nextInt();
        int[][] parentsList = new int[n + 1][21];
        int[] depth = new int[n + 1];
        int[][] min = new int[n + 1][21];
        precomputeParents(1, 0, adj, parentsList, weight, depth, min);
        for(int i = 1; i <= n; i++){
            System.out.print(i+" : ");
            for(int j = 0; j <= 20; j++) System.out.print(parentsList[i][j] + " ");
            System.out.print(" | ");
            for(int j = 0; j <= 20; j++) System.out.print(min[i][j] + " ");
            System.out.println();
        }
        for(int i = 1; i <= q; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(getMinOnPath(a, b, parentsList, min, depth, weight));
        }
    }
    public static void precomputeParents(int node, int parent, List<Integer>[] adj, int[][] parentsList, int[] weight, int[] depth, int[][] min){
        depth[node] = depth[parent] + 1;
        parentsList[node][0] = parent;
        min[node][0] = Math.min(weight[node], weight[parent]);//first parent comparison
        for(int i = 1; i <= 20; i++){
            parentsList[node][i] = parentsList[parentsList[node][i - 1]][i - 1];
            min[node][i] = Math.min(min[node][i - 1], min[parentsList[node][i - 1]][i - 1]);
        }
        for(int child : adj[node])
            if(child != parent)
                precomputeParents(child, node, adj, parentsList, weight, depth, min);
    }
    public static int getMinOnPath(int a, int b, int[][] parentsList, int[][] min, int[] depth, int[] weight){
        if(depth[b] > depth[a]){ int t = a; a = b; b = t;}
        int diff = depth[a] - depth[b];
        int currMin = Integer.MAX_VALUE;
        for(int i = 0; i <= 20; i++){
            if((diff & (1 << i)) > 0){
                currMin = Math.min(currMin, min[a][i]);//carrying min of the range we will skip/jump over
                a = parentsList[a][i];
            }
        }
        if(a == b) return Math.min(currMin, weight[a]); //compare with weight of current node if in case a and b were same to begin with, w/o comparison min=MAX
        for(int i = 20; i >= 0; i--){
            if(parentsList[a][i] != parentsList[b][i]){
                currMin = Math.min(currMin, min[a][i]);
                currMin = Math.min(currMin, min[b][i]);
                a = parentsList[a][i];
                b = parentsList[b][i];
            }
        }
        return Math.min(Math.min(currMin, min[a][0]), Math.min(currMin, min[b][0]));
    }
}
//TC: O((Q+N).logN)