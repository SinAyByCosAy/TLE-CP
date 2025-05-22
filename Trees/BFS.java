package DPBootcamp.Trees;

import java.util.*;

public class BFS {
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
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> bfs = new ArrayList<>();
        queue.add(1);//root
        while(!queue.isEmpty()){
            int parent = queue.remove();//removing front element for processing
            for(int child : adj[parent])
                queue.add(child);
            bfs.add(parent);
        }
        System.out.println(bfs);
    }
}
