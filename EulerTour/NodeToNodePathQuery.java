package DPBootcamp.EulerTour;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//find node to node path sum, sum(lca, a) + sum(lca, b) - arr[lca]
public class NodeToNodePathQuery {
    static int time = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n + 1];
        for(int i = 1; i <= n; i++) arr[i] = sc.nextInt();
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
        int[] inTime = new int[n + 1];
        int[] outTime = new int[n + 1];
        dfs(1, 0, adj, inTime, outTime);
        int len = 2 * n;
        int[] euler = new int[len];
        for(int i = 1; i <= n; i++){
            euler[inTime[i]] = arr[i];
            euler[outTime[i]] = (-1) * arr[i];
        }
        long[] sgt = new long[4 * len];
        buildTree(1, 0, len - 1, sgt, euler);
        int powerLimit = 20;
        int[] depth = new int[n + 1];
        int[][] parentsList = new int[n + 1][powerLimit + 1];
        precomputeParents(adj, parentsList, 1, 0, 0, depth, powerLimit);
        for(int i = 1; i <= q; i++){
            int type = sc.nextInt();
            if(type == 1){
                int x = sc.nextInt();
                int val = sc.nextInt();
                update(1, 0, len - 1, inTime[x], val, sgt);
                update(1, 0, len - 1, outTime[x], (-1) * val, sgt);
            }else{
                int a = sc.nextInt();
                int b = sc.nextInt();
                int lca = lca(a, b, parentsList, depth, powerLimit);
                long ans = query(1, 0, len - 1, inTime[lca], inTime[a], sgt)
                        + query(1, 0, len - 1, inTime[lca], inTime[b], sgt)
                        - arr[lca];
                System.out.println(ans);
            }
        }
    }
    public static void precomputeParents(List<Integer>[] adj, int[][] parentsList, int node, int parent, int currDepth, int[] depth, int powerLimit){
        depth[node] = currDepth;//storing depths of each node
        parentsList[node][0] = parent;
        for(int i = 1; i <= powerLimit; i++) parentsList[node][i] = parentsList[parentsList[node][i - 1]][i - 1];
        for(int child : adj[node]){
            if(child != parent) precomputeParents(adj, parentsList, child, node, currDepth + 1, depth, powerLimit);
        }
    }//O(NlogN)
    public static int lca(int a, int b, int[][] parentsList, int[] depth, int powerLimit){
        if(depth[b] > depth[a]) {//let's keep node a as the lower one for any case
            int t = a;
            a = b;
            b = t;
        }
        int diff = depth[a] - depth[b];
        int newA = findKthParent(a, diff, parentsList, powerLimit); //O(logN)
        if(newA == b) return newA;
        return getLCA(newA, b, parentsList, powerLimit); //O(logN)
    }
    public static int findKthParent(int x, int y, int[][] parentsList, int powerLimit){
        for(int i = 0; i <= powerLimit; i++){
            if((y & (1 << i)) > 0) x = parentsList[x][i];
        }
        return x;
    }
    public static int getLCA(int a, int b, int[][] parentsList, int powerLimit){
        for(int i = powerLimit; i >= 0; i--){
            if(parentsList[a][i] != parentsList[b][i]){
                a = parentsList[a][i];
                b = parentsList[b][i];
            }
        }
        return parentsList[a][0];
    }
    public static void buildTree(int idx, int start, int end, long[] sgt, int[] euler){
        if(start == end){
            sgt[idx] = euler[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTree(2 * idx, start, mid, sgt, euler);
        buildTree(2 * idx + 1, mid + 1, end, sgt, euler);
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1];
    }
    public static void update(int idx, int start, int end, int target, int val, long[] sgt){
        if(start == end){
            sgt[idx] = val;
            return;
        }
        int mid = (start + end) / 2;
        if(target <= mid) update(2 * idx, start, mid, target, val, sgt);
        else update(2 * idx + 1, mid + 1, end, target, val, sgt);
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1];
    }
    public static long query(int idx, int start, int end, int l, int r, long[] sgt){
        if(end < l || start > r) return 0;
        if(start >= l && end <= r) return sgt[idx];
        int mid = (start + end) / 2;
        return query(2 * idx, start, mid, l, r, sgt) + query(2 * idx + 1, mid + 1, end, l, r, sgt);
    }
    public static void dfs(int node, int parent, List<Integer>[] adj, int[] inTime, int[] outTime){
        inTime[node] = time++;
        for(int neighbor : adj[node])
            if(neighbor != parent) dfs(neighbor, node, adj, inTime, outTime);
        outTime[node] = time++;
    }
}
//TC: O((Q+N).logN)