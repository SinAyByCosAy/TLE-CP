//Replace With the Previous, Minimize
package DPBootcamp.Graphs;

import java.util.Scanner;

public class ReplaceWithPreviousMin {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();
            DSU6.init(26);
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < n; i++){
                char ch = s.charAt(i);
                while(DSU6.findMinParent(ch - 'a') > 0 && k > 0){
                    int pos = DSU6.findMinParent(ch - 'a');
                    DSU6.unionSet(pos, pos - 1);
                    k--;
                }
                res.append((char) (DSU6.findMinParent(ch - 'a') + 'a'));
            }
            System.out.println(res.toString());
        }
    }
}
class DSU6{
    static int[] parent, rank, minParent;
    public static void init(int n){
        parent = new int[n];
        rank = new int[n];
        minParent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
            minParent[i] = i;
        }
    }
    public static int findSet(int v){
        if(parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }
    public static void unionSet(int x, int y){
        int a = findSet(x);
        int b = findSet(y);
        if(a != b){
            if(rank[a] < rank[b]){ int t = a; a = b; b = t;}
            parent[b] = a;
            if(rank[a] == rank[b]) rank[a] ++;
            minParent[a] = Math.min(minParent[a], minParent[b]);
        }
    }
    public static int findMinParent(int v){
        return minParent[findSet(v)];
    }
}
//TC: O(N)