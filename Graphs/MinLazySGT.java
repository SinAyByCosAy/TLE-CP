package DPBootcamp.Graphs;

import java.util.Scanner;

//query type:
//1 : L R X, add X to all elements in [L, R]
//2 : L R, get the min of all values in [L, R]
public class MinLazySGT {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int[] sgt = new int[4 * n];
        int[] lsgt = new int[4 * n];
        buildTree(1, 0, n - 1, arr, sgt);
        for(int i = 1; i <= q; i++){
            int type = sc.nextInt();
            if(type == 1){
                int l = sc.nextInt();
                int r = sc.nextInt();
                int x = sc.nextInt();
                update(1, 0, n - 1, l, r, x, sgt, lsgt);
            }else{
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(query(1, 0, n - 1, l, r, sgt, lsgt));
            }
        }
    }
    public static void buildTree(int idx, int start, int end, int[] arr, int[] sgt){
        if(start == end){
            sgt[idx] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTree(2 * idx, start, mid, arr, sgt);
        buildTree(2 * idx + 1, mid + 1, end, arr, sgt);
        sgt[idx] = Math.min(sgt[2 * idx], sgt[2 * idx + 1]);
    }
    public static void update(int idx, int start, int end, int l, int r, int x, int[] sgt, int[] lsgt){
        if(end < l || start > r) return;
        if(start >= l && end <= r){//complete overlap
            // since all values in range will increase by x, min will also increase by x
            sgt[idx] += x;
            lsgt[idx] += x;
            return;
        }
        //partial overlap
        int mid = (start + end) / 2;
        updateChildren(idx, sgt, lsgt);
        update(2 * idx, start, mid, l, r, x, sgt, lsgt);
        update(2 * idx + 1, mid + 1, end, l, r, x, sgt, lsgt);
        sgt[idx] = Math.min(sgt[2 * idx], sgt[2 * idx + 1]);
    }
    public static int query(int idx, int start, int end, int l, int r, int[] sgt, int[] lsgt){
        if(end < l || start > r) return Integer.MAX_VALUE;
        if(start >= l && end <= r) return sgt[idx];
        int mid = (start + end) / 2;
        updateChildren(idx, sgt, lsgt);
        return Math.min(query(2 * idx, start, mid, l, r, sgt, lsgt),
                query(2 * idx + 1, mid + 1, end, l, r, sgt, lsgt));
    }
    public static void updateChildren(int idx, int[] sgt, int[] lsgt){
        //update child nodes
        sgt[2 * idx] += lsgt[idx];
        sgt[2 * idx + 1] += lsgt[idx];
        //update lsgt nodes
        lsgt[2 * idx] += lsgt[idx];
        lsgt[2 * idx + 1] += lsgt[idx];
        lsgt[idx] = 0;
    }
}
