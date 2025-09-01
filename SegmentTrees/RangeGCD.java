package DPBootcamp.SegmentTrees;

import java.util.Scanner;

//Given an array, find the number of ranges[l, r] s.t. gcd(l, r) >= x
//ex - arr: {1, 2, 3, 6, 9} x = 3, => 6
public class RangeGCD {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int log = (int) (Math.log(n) / Math.log(2));
        int[][] st = new int[log + 1][n];
        for(int i = 0; i < n; i++) st[0][i] = arr[i];
        for(int i = 1; i <= log; i++){
            for(int j = 0; j + (1 << i) <= n; j++)
                st[i][j] = gcd(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
        }
        int[] logTable = new int[n];
        for(int i = 2; i < n; i++) logTable[i] = 1 + logTable[i / 2];
        int ans = 0;
        for(int i = 0; i < n; i++){
            int curr = -1, left = i, right = n - 1;
            while(left <= right){
                int mid = (left + right) / 2;
                if(query(st, i, mid, logTable) >= x){
                    curr = mid;
                    left = mid + 1;
                }else right = mid - 1;
            }
            if(curr != -1)
                ans += (curr - i + 1);
        }
        System.out.println(ans);
    }
    public static int query(int[][] st, int l, int r, int[] logTable){
        int len = (r - l + 1);
        int p = logTable[len];
        return gcd(st[p][l], st[p][r - (1 << p) + 1]);
    }
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
