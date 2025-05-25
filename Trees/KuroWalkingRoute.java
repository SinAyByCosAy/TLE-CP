//https://codeforces.com/problemset/problem/979/C
package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KuroWalkingRoute {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        boolean[] isInvalid = new boolean[n + 1];
        //we make one end of invalid path as root and look for the path of the other end and mark it
        markInvalid(x, -1, y, adj, isInvalid);

        int s1 = getSubtreeSize(x, -1, adj, isInvalid);
        int s2 = getSubtreeSize(y, -1, adj, isInvalid);

        long ans = 1l * n * (n - 1) - 1l * s1 * s2;
        System.out.println(ans);
    }
    public static boolean markInvalid(int node, int parent, int target, List<Integer>[] adj, boolean[] isInvalid){
        //found the target node, now need to mark the path while returning
        if(node == target) { isInvalid[node] = true; return true; }

        boolean found = false;
        for(int child : adj[node]){//we check the paths of each child
            if(child != parent) found |= markInvalid(child, node, target, adj, isInvalid);
        }
        //from some node we'll get the value as true and it'll be true till our root(other end of invalid node)
        return isInvalid[node] = found;
    }
    public static int getSubtreeSize(int node, int parent, List<Integer>[] adj, boolean[] isInvalid){
        int count = 1;
        for(int child : adj[node]){
            if(child != parent && !isInvalid[child]) count += getSubtreeSize(child, node, adj, isInvalid);
        }
        return count;
    }
}
//TC: O(N)