package DPBootcamp.SegmentTrees;

import java.util.Scanner;

//query 1 : [L, R] - Add 1 to A[L], 2 to A[L + 1] ... R-L+1 to A[R]
//query 2 : [L, R] - return SUM
public class LazySGT_AP {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int[] sgt = new int[4 * n];
        Pair[] lsgt = new Pair[4 * n];
        for(int i = 0; i < 4 * n; i++) lsgt[i] = new Pair(0, 0);
        buildTree(1, 0, n - 1, arr, sgt);
        for(int i = 1; i <= q; i++){
            int type = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            if(type == 1) update(1, 0, n - 1, l, r, sgt, lsgt);
            else System.out.println(query(1, 0, n - 1, l, r, sgt, lsgt));
        }
    }
    private static void buildTree(int idx, int start, int end, int[] arr, int[] sgt){
        if(start == end) {
            sgt[idx] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTree(2 * idx, start, mid, arr, sgt);
        buildTree(2 * idx + 1, mid + 1, end, arr, sgt);
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1];
    }
    //to find starting points of AP for any segment we can use the nth term formula
    //t(n) = a + (n - 1).d, where 1 is the starting point. Replace 1 with starting point, if required.
    public static void update(int idx, int start, int end, int l, int r, int[] sgt, Pair[] lsgt){
        if(end < l || start > r) return;
        if(start >= l && end <= r){
            int d = 1;
            int a = 1;
            a += (start - l) * d;
            int n = end - start + 1;
            sgt[idx] += (n * (2 * a + (n - 1) * d)) / 2;
            lsgt[idx].a += a;
            lsgt[idx].d += d;
            return;
        }
        int mid = (start + end) / 2;
        updateChildren(idx, start, mid, end, sgt, lsgt);
        update(2 * idx, start, mid, l, r, sgt, lsgt);
        update(2 * idx + 1, mid + 1, end, l, r, sgt, lsgt);
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1];
    }
    public static int query(int idx, int start, int end, int l, int r, int[] sgt, Pair[] lsgt){
        if(end < l || start > r) return 0;
        if(start >= l && end <= r) return sgt[idx];
        int mid = (start + end) / 2;
        updateChildren(idx, start, mid, end, sgt, lsgt);
        return query(2 * idx, start, mid, l, r, sgt, lsgt) + query(2 * idx + 1, mid + 1, end, l, r, sgt, lsgt);
    }
    public static void updateChildren(int idx, int start, int mid, int end, int[] sgt, Pair[] lsgt){
        int seg1_start = start, seg1_end = mid;
        int seg2_start = mid + 1, seg2_end = end;
        int seg1_a = lsgt[idx].a;
        int d = lsgt[idx].d;
        int seg2_a = seg1_a + (seg2_start - start) * d;
        int seg1_n = seg1_end - seg1_start + 1;
        int seg2_n = seg2_end - seg2_start + 1;
        int sum1 = seg1_n * (2 * seg1_a + (seg1_n - 1) * d) / 2;
        int sum2 = seg2_n * (2 * seg2_a + (seg2_n - 1) * d) / 2;
        sgt[2 * idx] += sum1;
        sgt[2 * idx + 1] += sum2;

        lsgt[2 * idx].a += seg1_a;
        lsgt[2 * idx].d += d;
        lsgt[2 * idx + 1].a += seg2_a;
        lsgt[2 * idx + 1].d += d;
        
        lsgt[idx].a = 0;
        lsgt[idx].d = 0;
    }
}
class Pair{
    int a, d;
    Pair(int a, int d){
        this.a = a;
        this.d = d;
    }
}