package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//smart way to find diameter of a tree
public class InTimeOutTime {
    static int t = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int parent = sc.nextInt();
            int child = sc.nextInt();
            adj[parent].add(child);
            adj[child].add(parent);
        }
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        setTime(1, -1, adj, in, out);
        int q = sc.nextInt();
        for(int i = 1; i <= q; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println((in[x] < in[y] && out[y] < out[x]) ? "YES" : "NO");
        }
        System.out.println(Arrays.toString(in));
        System.out.println(Arrays.toString(out));
    }
    public static void setTime(int node, int parent, List<Integer>[] adj, int[] in, int[] out){
        in[node] = t++;

        for(int child : adj[node]){
            if(child != parent) setTime(child, node, adj, in, out);
        }

        out[node] = t++;
    }
}
//TC: O(N)