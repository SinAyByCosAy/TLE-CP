package DPBootcamp.Graphs;

import java.util.Arrays;
import java.util.Scanner;

//Simple Disjoint set union implementation
public class DisjointSetUnion {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        DSU2.init(n);
        for(int i = 1; i <= q; i++){
            int type = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(type == 1) DSU2.unionSet(x, y);
            if(type == 2) System.out.println((DSU2.findSet(x) == DSU2.findSet(y)) ? "YES" : "NO");
        }
    }
}
class DSU2{
    private static int[] parent, rank;
    public static void init(int n){
        parent = new int[n + 1];
        rank = new int[n + 1];
        Arrays.fill(rank, 0);
        for(int i = 1; i <= n; i++) parent[i] = i;//self parent
    }
    public static int findSet(int x){//we are looking for root
        if(parent[x] == x) return x; //self parent means node is root
        return parent[x] = findSet(parent[x]); //otherwise we get the root from parent and memoize
    }
    public static void unionSet(int x, int y){
        int a = findSet(x); //find root
        int b = findSet(y);
        if(a != b){//edge doesn't exist, part of different components
            if(rank[a] < rank[b]){ int t = a; a = b; b = t; }//we make sure to merge b -> a
            parent[b] = a; //merged
            if(rank[a] == rank[b]) rank[a]++; //if rank is same, we need to increase
        }
    }
}
//TC per query: O(IAF(N)), IAF - Inverse Ackerman function
