package DPBootcamp.SegmentTrees;

import java.util.Scanner;

public class LazySGT {
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
            if(type == 1) { //update
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
    public static void buildTree(int idx, int s, int e, int[] arr, int[] sgt){
        if(s == e){
            sgt[idx] = arr[s];
            return;
        }
        int m = (s + e) / 2;
        buildTree(2 * idx, s, m, arr, sgt);
        buildTree(2 * idx + 1, m + 1, e, arr, sgt);
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1];
    }
    public static void update(int idx, int s, int e, int l, int r, int x, int[] sgt, int[] lsgt){
        if(l > e || s > r) return; //disjoint
        if(s >= l && e <= r){//complete overlap
            sgt[idx] += (e - s + 1) * x;
            lsgt[idx] += x;
            return;
        }
        //partial overlap
        //update children first
        int m = (s + e) / 2;
        updateChildren(idx, s, m, e, sgt, lsgt);

        //process the query
        update(2 * idx, s, m, l, r, x, sgt, lsgt);
        update(2 * idx + 1, m + 1, e, l, r, x, sgt, lsgt);
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1];
    }
    public static int query(int idx, int s, int e, int l, int r, int[] sgt, int[] lsgt){
        if(l > e || s > r) return 0; //disjoint
        if(s >= l && e <= r){//complete overlap
            return sgt[idx];
        }
        //partial overlap
        //update children nodes
        int m = (s + e) / 2;
        updateChildren(idx, s, m, e, sgt, lsgt);

        //get the values from both side
        return query(2 * idx, s, m, l, r, sgt, lsgt) + query(2 * idx + 1, m + 1, e, l, r, sgt, lsgt);
    }
    public static void updateChildren(int idx, int s, int m, int e, int[] sgt, int[] lsgt){
        //updating child nodes
        sgt[2 * idx] += (m - s + 1) * lsgt[idx];
        sgt[2 * idx + 1] += (e - m) * lsgt[idx];
        //updating LSGT nodes
        lsgt[2 * idx] += lsgt[idx];
        lsgt[2 * idx + 1] += lsgt[idx];
        lsgt[idx] = 0;
    }
}
