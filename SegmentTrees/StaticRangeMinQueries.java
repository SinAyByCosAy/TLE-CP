//https://cses.fi/problemset/task/1647
package DPBootcamp.SegmentTrees;

import java.util.Arrays;
import java.util.Scanner;

public class StaticRangeMinQueries {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int log = (int) (Math.log(n) / Math.log(2));
        int[][] st = new int[log + 1][n];
        for(int[] row : st) Arrays.fill(row, Integer.MAX_VALUE);
        for(int i = 0; i < n; i++) st[0][i] = arr[i];
        for(int i = 1; i <= log; i++)
            for(int j = 0; j + (1 << i) <= n; j++)
                st[i][j] = Math.min(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);

        int[] logTable = new int[n + 1];
        for(int i = 2; i <= n; i++) logTable[i] = 1 + logTable[i / 2];

        for(int i = 1; i <= q; i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            l--;
            r--;
            System.out.println(query(st, l, r, logTable));
        }
    }
    public static int query(int[][] st, int l, int r, int[] logTable){
        int len = (r - l + 1);
        int p = logTable[len];
        return Math.min(st[p][l], st[p][r - (1 << p) + 1]);
    }
}
