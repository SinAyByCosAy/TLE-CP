package DPBootcamp.SegmentTrees;

import java.util.Arrays;
import java.util.Scanner;

//query 1 : [L, R, X] - change all values in range [L, R] to X
//query 2 : [L, R] - get the XOR of all values in this range
public class LazySGTxor {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int[] sgt = new int[4 * n];
        int[] lsgt = new int[4 * n];
        Arrays.fill(lsgt, -1); //0 holds significance in this scenario, can't use it as default value
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
    public static void buildTree(int idx, int start, int end, int[] arr, int[] sgt){
        if(start == end){
            sgt[idx] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTree(2 * idx, start, mid, arr, sgt);
        buildTree(2 * idx + 1, mid + 1, end, arr, sgt);
        sgt[idx] = sgt[2 * idx] ^ sgt[2 * idx + 1];
    }
    public static void update(int idx, int start, int end, int l, int r, int x, int[] sgt, int[] lsgt){
        if(end < l || start > r) return;
        if(start >= l && end <= r){
            if((end - start + 1) % 2 == 0) sgt[idx] = 0;
            else sgt[idx] = x;
            lsgt[idx] = x; //not lsgt[idx] += x because it's a complete change of the elements in the range to x, no sum or no old value needed.
            return;
        }
        int mid = (start + end) / 2;
        if(lsgt[idx] != -1) updateChildren(idx, start, mid, end, sgt, lsgt); //we didn't need a check for sum as adding 0 didn't do any harm. Here we can't process the default value.
        update(2 * idx, start, mid, l, r, x, sgt, lsgt);
        update(2 * idx + 1, mid + 1, end, l, r, x, sgt, lsgt);
        sgt[idx] = sgt[2 * idx] ^ sgt[2 * idx + 1];
    }
    public static int query(int idx, int start, int end, int l, int r, int[] sgt, int[] lsgt){
        if(end < l || start > r) return 0;
        if(start >= l && end <= r) return sgt[idx];
        int mid = (start + end) / 2;
        if(lsgt[idx] != -1) updateChildren(idx, start, mid, end, sgt, lsgt);
        return query(2 * idx, start, mid, l, r, sgt, lsgt) ^ query(2 * idx + 1, mid + 1, end, l, r, sgt, lsgt);
    }
    public static void updateChildren(int idx, int start, int mid, int end, int[] sgt, int[] lsgt){
        if((mid - start + 1) % 2 == 0) sgt[2 * idx] = 0;
        else sgt[2 * idx] = lsgt[idx];
        if((end - mid) % 2 == 0) sgt[2 * idx + 1] = 0;
        else sgt[2 * idx + 1] = lsgt[idx];

        lsgt[2 * idx] = lsgt[idx];
        lsgt[2 * idx + 1] = lsgt[idx];
        lsgt[idx] = -1;
    }
}
