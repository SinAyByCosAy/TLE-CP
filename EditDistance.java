package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class EditDistance {
    static int dp[][];
    static String s1, s2;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        s1 = sc.nextLine();
        s2 = sc.nextLine();
        int n = s1.length();
        int m = s2.length();
        dp = new int[n][m];
        for(int[] row: dp)
            Arrays.fill(row, -1);
        System.out.println(ed(n-1, m-1));
    }
    static int ed(int i, int j){
        if(i < 0 || j < 0)
            return Math.abs(i-j);
        if(dp[i][j] != -1)
            return dp[i][j];
        int ans = 0;
        if(s1.charAt(i) == s2.charAt(j))
            ans = ed(i-1,j-1);
        else{
            ans = 1 + Math.min(ed(i, j-1), Math.min(ed(i-1, j-1), ed(i-1,j)));
        }
        dp[i][j] = ans;
        return ans;
    }
}
