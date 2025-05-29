package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//find k-th parent of a node using Binary lifting in O(log N) time
public class BinaryLifting {
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
        int powerLimit = 20;//log(2e5) ~ 18
        int[][] parentsList = new int[n + 1][powerLimit];
        //pre computing 2^bit(1, 2, 4, 8) parents for each node
        precomputeParents(1, 0, adj, parentsList, powerLimit); //O(NlogN)
        int q = sc.nextInt();
        for(int i = 1; i <= q; i++){//O(QlogN)
            //find y-th parent of x
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println(getKthParent(parentsList, x, y, powerLimit));
        }
    }
    public static void precomputeParents(int node, int parent, List<Integer>[] adj, int[][] parentsList, int powerLimit){
        parentsList[node][0] = parent;
        for(int i = 1; i < powerLimit; i++) parentsList[node][i] = parentsList[parentsList[node][i - 1]][i - 1];
        for(int child : adj[node])
            if(child != parent) precomputeParents(child, node, adj, parentsList, powerLimit);
    }
    public static int getKthParent(int[][] parentsList, int x, int y, int powerLimit){
        int curr = x;
        for(int i = 0; i < powerLimit; i++){
            if((y & (1 << i)) > 0) curr = parentsList[curr][i];
        }
        return curr;
    }
}
//TC: O((N + Q) . logN)