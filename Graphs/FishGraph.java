//https://codeforces.com/problemset/problem/1817/B
package DPBootcamp.Graphs;

import java.util.*;

public class FishGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            List<Integer>[] adj = new ArrayList[n + 1];
            for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for(int i = 1; i <= m; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                adj[x].add(y);
                adj[y].add(x);
            }
            int specialNode = -1, start = -1, end = -1;
            boolean fish = false;
            int[] parent = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            Set<Integer> usedEdges = new HashSet<>();
            Set<Integer> unusedEdges = new HashSet<>();
            for(int i = 1; i <= n; i++){
                int limit = adj[i].size();
                if(limit >= 4){
                    //we now need to make pairs of starting and ending node
                    for(int k = 0; k < limit; k++){
                        int a = adj[i].get(k);
                        //now we need to find a path b/w start and end
                        Queue<Integer> q = new LinkedList<>();
                        Arrays.fill(parent, 0);
                        Arrays.fill(visited, false);
                        q.add(a);
                        visited[a] = true;
                        while(!q.isEmpty()){
                            int top = q.poll();
                            for(int neighbor : adj[top]){
                                if(!visited[neighbor] && neighbor != i){
                                    q.add(neighbor);
                                    visited[neighbor] = true;
                                    parent[neighbor] = top;
                                }
                            }
                        }
                        int b;
                        for(int neighbor : adj[i]) {
                            usedEdges.clear();
                            unusedEdges.clear();
                            if(visited[neighbor] && neighbor != a) {
                                //cycle exists, can exist with multiple nodes, try them one by one
                                b = neighbor;
                                int idx = b;
                                while (idx != 0) {
                                    usedEdges.add(idx);
                                    idx = parent[idx];
                                }
                                for (int neigh : adj[i]) {
                                    if (!usedEdges.contains(neigh)) unusedEdges.add(neigh);
                                }
                                if (unusedEdges.size() >= 2) {
                                    specialNode = i;
                                    fish = true;
                                    start = a;
                                    end = b;
                                    break;
                                }
                            }
                        }
                        if(fish) break;
                    }
                    if(fish) break;
                }
                if(fish) break;
            }
            if(fish){
                System.out.println("YES");
                System.out.println(usedEdges.size() + 3);
                int idx = end;
                while(idx != start){
                    System.out.println(idx + " " + parent[idx]);
                    idx = parent[idx];
                }
                System.out.println(specialNode + " " + start);
                System.out.println(specialNode + " " + end);
                int count = 0;
                for(int ele : unusedEdges){
                    count++;
                    System.out.println(ele + " " + specialNode);
                    if(count == 2) break;
                }
            }
            else System.out.println("NO");
        }
    }
}
//TC: O(N + M)