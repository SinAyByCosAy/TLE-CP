package DPBootcamp.Graphs;

import java.util.Scanner;

public class RangeAlternateSum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if((i & 1) > 0) arr[i] = (-1) * arr[i];
        }
        int[] sgt = new int[4 * n];
        buildSGT(1, 0, n - 1, sgt, arr);
        int q = sc.nextInt();
        for(int i = 1; i <= q; i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            int val = query(1, 0, n - 1, l, r, sgt);
            if((l & 1) > 0) val *= -1;
            System.out.println(val);
        }
    }
    public static void buildSGT(int idx, int start, int end, int[] sgt, int[] arr){
        if(start == end){
            sgt[idx] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        buildSGT(2 * idx, start, mid, sgt, arr);
        buildSGT(2 * idx + 1, mid + 1, end, sgt, arr);
        sgt[idx] = sgt[2 * idx] + sgt[2 * idx + 1];
    }
    public static int query(int idx, int start, int end, int l, int r, int[] sgt){
        if(start > r || end < l) return 0;
        if(start >= l && end <= r) return sgt[idx];
        int mid = (start + end) / 2;
        return query(2 * idx, start, mid, l, r, sgt) + query(2 * idx + 1, mid + 1, end, l, r, sgt);
    }
}
