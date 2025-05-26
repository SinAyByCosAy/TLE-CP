//https://codeforces.com/contest/1946/problem/C
package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Apply BS on size(x) of subtree, check if we can make k + 1 cuts on subtrees with x size
public class TreeCutting {
    static int cuts = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            List<Integer>[] adj = new ArrayList[n + 1];
            for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for(int i = 1; i < n; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }
            System.out.println(getMaxSize(adj, n, k));
        }
    }
    public static int getMaxSize(List<Integer>[] adj, int n, int k){
        int l = 0, r = n;
        int size = 0;
        while(l <= r){
            int mid = (l + r) / 2;
            if(checkCuts(adj, mid, k)){
                size = mid;
                l = mid + 1;
            }else r = mid - 1;
        }
        return size;
    }
    public static boolean checkCuts(List<Integer>[] adj, int targetSize, int k){
        cuts = 0;
        count(1, -1, adj, targetSize);
        return cuts >= k + 1;
    }
    public static int count(int node, int parent, List<Integer>[] adj, int targetSize){
        int size = 1;
        for(int child : adj[node]){
            if(child != parent) size += count(child, node, adj, targetSize);
        }
        if(size >= targetSize){
            size = 0;
            cuts++;
        }
        return size;
    }
}
//TC: O(N . log N)