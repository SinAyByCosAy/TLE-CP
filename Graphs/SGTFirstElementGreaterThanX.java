package DPBootcamp.Graphs;

import java.util.Scanner;

//given an array of n elements and q queries.
//query type 1: (pos, x) : update a[pos] = x
//query type 2: (l,r,x) : get the first index with value > x

public class SGTFirstElementGreaterThanX {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int[] sgt = new int[4 * n];
        buildTree(1, 0, n - 1, arr, sgt);
        int q = sc.nextInt();
        for(int i = 1; i <= q; i++){
            int type = sc.nextInt();
            if(type == 1){
                int idx = sc.nextInt();
                int val = sc.nextInt();
                update(1, 0, n - 1, idx, val, sgt);
            }else{
                int l = sc.nextInt();
                int r = sc.nextInt();
                int val = sc.nextInt();
                System.out.println(rangeQuery(l, r, val, n, sgt));
            }
        }
    }
    private static void buildTree(int idx, int start, int end, int[] arr, int[] sgt){
        if(start == end){
            sgt[idx] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTree(2 * idx, start, mid, arr, sgt);
        buildTree(2 * idx + 1, mid + 1, end, arr, sgt);
        sgt[idx] = Math.max(sgt[2 * idx], sgt[2 * idx + 1]);
    }
    private static void update(int idx, int start, int end, int target, int val, int[] sgt){
        if(start == end){
            sgt[idx] = val;
            return;
        }
        int mid = (start + end) / 2;
        if(target <= mid) update(2 * idx, start, mid, target, val, sgt);
        else update(2 * idx + 1, mid + 1, end, target, val, sgt);
        sgt[idx] = Math.max(sgt[2 * idx], sgt[2 * idx + 1]);
    }
    private static int rangeQuery(int l, int r, int x, int n, int[] sgt){
        int val = query(1, 0, n - 1, l, r, sgt);
        int ans = -1;
        if(val < x) return ans;
        while(l <= r){ //O(logN)
            int mid = (l + r) / 2;
            if(query(1, 0, n - 1, l, mid, sgt) > x){ //O(logN)
                ans = mid;
                r = mid - 1;
            }else l = mid + 1;
        }
        return ans;
    }
    private static int query(int idx, int start, int end, int l, int r, int[] sgt){
        if(l > end || r < start) return Integer.MIN_VALUE;
        if(start >= l && end <= r) return sgt[idx];
        int mid = (start + end) / 2;
        return Math.max(query(2 * idx, start, mid, l, r, sgt), query(2 * idx + 1, mid + 1, end, l, r, sgt));
    }
}
//TC: O(log^2 (N))