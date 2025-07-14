//https://codeforces.com/contest/150/problem/B
package DPBootcamp.Graphs;

import java.util.Scanner;

public class QuantityOfStringsII {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        DSU.init(n);
        for(int i = 0; i <= (n - k); i++){
            int l = i, r = i + k - 1;
            while(l < r){
                DSU.unionSet(l, r);
                l++;
                r--;
            }
        }
        System.out.println(binaryExpo(m, DSU.getComponents()));
    }
    public static int binaryExpo(long a, long n){
        int mod = (int) 1e9 + 7;
        long res = 1;
        while(n > 0){
            if((n & 1) > 0){
                res *= a;
                res %= mod;
            }
            a *= a;
            a %= mod;
            n >>= 1;
        }
        return (int) res;
    }
}
class DSU{
    private static int[] parent, rank;
    private static int compCount;
    public static void init(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i = 1; i < n; i++) parent[i] = i;
        compCount = n;
    }
    public static int findSet(int v){
        if(parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }
    public static void unionSet(int x, int y){
        int a = findSet(x);
        int b = findSet(y);
        if(a != b){
            if(rank[b] > rank[a]){ int t = a; a = b; b = t;}
            parent[b] = a;
            if(rank[a] == rank[b]) rank[a]++;
            compCount--;
        }
    }
    public static int getComponents(){ return compCount; }
}
//TC: O(N.K)