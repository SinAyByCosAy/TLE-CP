package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DFS {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
//        dfs(1, -1, adj);
        calcLvl(1, -1, 0, adj);
        calcHeight(1, -1, adj);
    }

    //normal dfs
    public static void dfs(int node, int parent, List<Integer>[] adj){
        System.out.println(node);
        for(int ele : adj[node])
            if(ele != parent) dfs(ele, node, adj);
    }

    //level of each node
    public static void calcLvl(int node, int parent, int level, List<Integer>[] adj){
        System.out.println(node+", lvl: "+level);
        for(int ele : adj[node])
            if(ele != parent) calcLvl(ele, node, level + 1, adj);
    }

    //Height of each node
    public static int calcHeight(int node, int parent, List<Integer>[] adj){
        int height = 0;
        for(int ele : adj[node])
            if(ele != parent) height = Math.max(height, calcHeight(ele, node, adj) + 1);
        System.out.println("height of "+node+" = "+height);
        return height;
    }
}
