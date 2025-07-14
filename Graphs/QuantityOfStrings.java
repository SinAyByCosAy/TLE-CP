//https://codeforces.com/contest/150/problem/B
package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuantityOfStrings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        //adding edges between connected nodes
        for(int i = 0; i <= (n-k); i++){
            int l = i, r = i + k - 1;
            while(l < r){
                adj[l].add(r);
                adj[r].add(l);
                l++;
                r--;
            }
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                count++;
                dfs(adj, visited, i);
            }
        }
        System.out.println(binaryExpo(m, count));
    }
    public static void dfs(List<Integer>[] adj, boolean[] visited, int node){
        visited[node] = true;
        for(int neighbor : adj[node]){
            if(!visited[neighbor]) dfs(adj, visited, neighbor);
        }
    }
    public static int binaryExpo(long a, long n){
        int mod = (int) 1e9 + 7;
        long res = 1;
        while(n >  0){
            if((n & 1) == 1){
                res *= 1l * a;
                res %= mod;
            }
            a *= a;
            a %= mod;
            n >>= 1;
        }
        return (int) res;
    }
}
//TC: O(NK)