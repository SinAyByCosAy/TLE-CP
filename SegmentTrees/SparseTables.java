package DPBootcamp.SegmentTrees;

import java.util.Arrays;
import java.util.Scanner;

public class SparseTables {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int log = (int) (Math.log(n) / Math.log(2)); //log N powers will fit in
        int[][] st = new int[log + 1][n];
        for(int[] rows : st) Arrays.fill(rows, Integer.MAX_VALUE);
        for(int i = 0; i < n; i++) st[0][i] = arr[i];

        for(int i = 1; i <= log; i++){
            for(int j = 0; j + (1 << i) <= n; j++){
                st[i][j] = Math.min(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
            }
        }
        for(int[] row : st) System.out.println(Arrays.toString(row));
    }
}
