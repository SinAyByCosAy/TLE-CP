//https://codeforces.com/problemset/problem/1133/E
package DPBootcamp.DP;

import java.util.Arrays;
import java.util.Scanner;

public class KBalancedTeams {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        int[] maxRight = new int[n];
        findMaxRight(arr, maxRight, n);

        int[][] dp = new int[n][k + 1];
        System.out.println(getMaxStudents(maxRight, dp, k, n));
    }
    public static void findMaxRight(Integer[] arr, int[] maxRight, int n){
        int p1 = 0, p2 = 0;
        while(p1 < n){
            while(p2 < n && arr[p2] - arr[p1] <= 5) p2++;
            maxRight[p1] = p2 - 1;
            p1++;
        }
    }
    public static int getMaxStudents(int[] maxRight, int[][] dp, int k, int n){
        for(int i = n - 1; i >= 0; i--){
            for(int j = k - 1; j >= 0; j--){
                dp[i][j] = (i + 1 == n) ? 0 : dp[i + 1][j];
                dp[i][j] = Math.max(dp[i][j],
                        (maxRight[i] - i + 1) + ((maxRight[i] + 1 == n) ? 0 : dp[maxRight[i] + 1][j + 1]));
            }
        }
        return dp[0][0];
    }
    public static int getMaxStudentsRecur(int[] maxRight, int[][] dp, int idx, int teamsCount, int k, int n){
        if(idx == n || teamsCount == k) return 0;
        if(dp[idx][teamsCount] != -1) return dp[idx][teamsCount];

        int ans = getMaxStudentsRecur(maxRight, dp, idx + 1, teamsCount, k, n);
        ans = Math.max(ans, (maxRight[idx] - idx + 1) + getMaxStudentsRecur(maxRight, dp, maxRight[idx] + 1, teamsCount + 1, k, n));

        return dp[idx][teamsCount] = ans;
    }
}
//TC: O(N.K)