package DPBootcamp.SegmentTrees;

import java.util.Arrays;
import java.util.Scanner;

//finding range sum using sparse table
public class SparseTableSum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int log = (int) (Math.log(n) / Math.log(2));
        int[][] st = new int[log + 1][n];
        for(int[] row : st) Arrays.fill(row, 0);
        for(int i = 0; i < n; i++) st[0][i] = arr[i];

        for(int i = 1; i <= log; i++){
            for(int j = 0; j + (1 << i) <= n; j++){
                st[i][j] = st[i - 1][j] + st[i - 1][j + (1 << (i- 1))];
            }
        }

        int q = sc.nextInt();
        for(int i = 1; i <= q; i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(query(st, l, r));
        }
    }
    public static int query(int[][] st, int l, int r){
        int len = r - l + 1;
        int idx = l;
        int sum = 0;
        for(int i = 31; i >= 0; i--){
            if((len & (1 << i)) > 0){
                sum += st[i][idx];
                idx += (1 << i);
            }
        }
        return sum;
    }
}