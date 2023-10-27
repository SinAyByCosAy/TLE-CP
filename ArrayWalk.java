//https://codeforces.com/problemset/problem/1389/B
package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayWalk {
    static int a[], dp[][];
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int z = sc.nextInt();
            a = new int[n];
            for(int i=0;i<n;i++){
                a[i] = sc.nextInt();
            }
            dp = new int[n][z+1];
            for(int[] row: dp)
                Arrays.fill(row, -1);
            int ans = a[0] + getScore(k, 1, z);
            System.out.println(ans);
        }
    }
    static int getScore(int k, int i, int z){
        if(k==0 || i<0 || i>=a.length)
            return 0;
        if(dp[i][z] != -1)
            return dp[i][z];
        int score = 0;
        if(i<a.length)
            score = Math.max(score, a[i] + getScore(k-1, i+1, z));
        if(z>0)
            score = Math.max(score, a[i] + getScore(k-1, i-1, z-1));
        dp[i][z] = score;
        return score;
    }
}
