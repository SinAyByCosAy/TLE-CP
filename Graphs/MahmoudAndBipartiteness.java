//https://codeforces.com/problemset/problem/862/B
package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MahmoudAndBipartiteness {
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
        int[] color = new int[n + 1];
        //no need for component loop as it's a tree
        //with one dfs call, the whole tree will get colored
        //no need for parent or visited array, we'll use color[] for decision making
        dfs(adj, color, 1, 1);
        int count1 = 0, count2 = 0;
        for(int i = 1; i <= n; i++){
            if (color[i] == 1) count1++;
            else count2++;
        }
        System.out.println((1l * count1 * count2) - (n - 1));
    }
    public static void dfs(List<Integer>[] adj, int[] color, int node, int prevColor){
        int currColor = prevColor == 1 ? 2 : 1;
        color[node] = currColor;
        for(int neighbor : adj[node]){
            if(color[neighbor] == 0) dfs(adj, color, neighbor, currColor);
            // we don't need to check for encountering a colored node.
            // Since it's a tree, only colored node that we encounter is going to be the parent
            // which for sure will be of a different color.
        }
    }
}
//TC: O(N)