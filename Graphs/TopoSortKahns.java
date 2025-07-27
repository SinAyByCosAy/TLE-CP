package DPBootcamp.Graphs;

import java.util.*;
//Topological sort in DAG using Kahn's(BFS)
public class TopoSortKahns {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        int[] inDeg = new int[n + 1];
        for(int i = 1; i <= m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            inDeg[y]++; //increasing degree of node where edge goes into
        }
        Queue<Integer> pq = new LinkedList<>();
        for(int i = 1; i <= n; i++)
            if(inDeg[i] == 0) pq.add(i); //nodes with in-deg 0 will go in first

        int idx = 0;
        int[] res = new int[n + 1];
        while(!pq.isEmpty()){
            int top = pq.poll();//all the nodes linking to it are processed, so current node can be placed
            res[idx++] = top;
            for(int neighbor : adj[top]){
                inDeg[neighbor]--;
                if(inDeg[neighbor] == 0) pq.add(neighbor);//node ready to be processed
            }
        }
        System.out.println(Arrays.toString(res));
    }
}
//TC: O(N + M)
//SC: O(N + M)