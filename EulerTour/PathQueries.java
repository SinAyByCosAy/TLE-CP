//https://cses.fi/problemset/task/1138/
package DPBootcamp.EulerTour;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathQueries {
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
        for(int i = 1; i <= q; i++){
            int type = sc.nextInt();
            if(type == 1){
                int x = sc.nextInt();
                int val = sc.nextInt();
                update(1, 0, len - 1, inTime[x], val, sgt);
                update(1, 0, len - 1, outTime[x], (-1) * val, sgt);
            }else{
                int x = sc.nextInt();
                System.out.println(query(1, 0, len - 1, inTime[1], inTime[x], sgt));
            }
        }
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
        for(int neighbor : adj[node]){
            if(neighbor != parent) dfs(neighbor, node, adj, inTime, outTime);
        }
        outTime[node] = time++;
    }
}
