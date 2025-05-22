package DPBootcamp.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//for each node, find the farthest node and it's distance
public class FarthestLeafNode {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int parent = sc.nextInt();
            int child = sc.nextInt();
            adj[parent].add(child);
        }
        Pair[] farthest = new Pair[n + 1];
        calculateFarthestNodes(1, adj, farthest);
        for(int i = 1; i <= n; i++) System.out.println("Node: "+i+" has farthest node: "+farthest[i].node+" at dist: "+farthest[i].dist);
    }
    public static void calculateFarthestNodes(int node, List<Integer>[] adj, Pair[] farthest){
        farthest[node] = new Pair(node, 0);
        for(int ele : adj[node]){//go to children and get their farthest nodes
            calculateFarthestNodes(ele, adj, farthest);
            if(farthest[node].dist < farthest[ele].dist + 1){
                farthest[node].dist = farthest[ele].dist + 1;
                farthest[node].node = farthest[ele].node;
            }
        }
    }
}
class Pair{
    int node, dist;
    Pair(int node, int dist ){
        this.node = node;
        this.dist = dist;
    }
}