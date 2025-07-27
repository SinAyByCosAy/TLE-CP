//https://cses.fi/problemset/task/1679/
package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CourseSchedule {
    static boolean flag = true;
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
        boolean[] inStack = new boolean[n + 1];
        boolean[] visited = new boolean[n + 1];
        List<Integer> res = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(!visited[i]) dfs(i, adj, visited, inStack, res);
        }
        Collections.reverse(res);
        if(flag){
            for(int ele : res) System.out.print(ele + " ");
            System.out.println();
        }else System.out.println("IMPOSSIBLE");
    }
    public static void dfs(int node, List<Integer>[] adj, boolean[] visited, boolean[] inStack, List<Integer> res){
        inStack[node] = visited[node] = true;
        for(int neighbor : adj[node]){
            if(!visited[neighbor]) dfs(neighbor, adj, visited, inStack, res);
            else if(inStack[neighbor]) flag = false; //node was still in the ongoing loop
        }
        inStack[node] = false;
        res.add(node);
    }
}
//TC: O(N + M)