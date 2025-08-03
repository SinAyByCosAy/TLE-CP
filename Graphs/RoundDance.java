//https://codeforces.com/problemset/problem/1833/E
package DPBootcamp.Graphs;

import java.util.*;

public class RoundDance {
    public static boolean isCycle;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            HashSet<Integer>[] adj = new HashSet[n + 1];
            for(int i = 1; i <=n; i++) adj[i] = new HashSet<>();
            for(int i = 1; i <= n; i++){
                int x = sc.nextInt();
                adj[i].add(x);
                adj[x].add(i);
            }
            int cycleCount = 0;
            int pathCount = 0;
            boolean[] visited = new boolean[n + 1];
            for(int i = 1; i <= n; i++){
                if(!visited[i]){
                    isCycle = false;
                    dfs(i, 0, adj, visited);
                    if(isCycle) cycleCount++;
                    else pathCount ++;
                }
            }
            int max = cycleCount + pathCount;
            int min = cycleCount + (pathCount > 0 ? 1 : 0);
            System.out.println(cycleCount +" "+ pathCount);

            System.out.println(min+" "+max);
        }
    }
    private static void dfs(int node, int parent, HashSet<Integer>[] adj, boolean[] visited){
        visited[node] = true;
        for(int neighbor : adj[node]){
            if(!visited[neighbor]) dfs(neighbor, node, adj, visited);
            else if(neighbor != parent) isCycle = true; //neighbor shouldn't be parent
        }
    }
}
//TC: O(N)