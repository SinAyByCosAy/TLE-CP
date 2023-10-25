package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class EditDistanceTabularDP {
    static int dp[][];
    static String s1, s2;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        s1 = sc.nextLine();
        s2 = sc.nextLine();
        int n = s1.length();
        int m = s2.length();
        dp = new int[n][m];
//        System.out.println(ed(n - 1, m - 1));
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = (i-1<0 || j-1<0) ? Math.abs(i-j) : dp[i-1][j-1];
                }else{
                    int replace = (i-1<0 || j-1<0) ? Math.abs(i-j) : dp[i-1][j-1];
                    int add = (j-1<0) ? Math.abs(i-j) : dp[i][j-1];
                    int remove = (i-1<0) ? Math.abs(i-j) : dp[i-1][j];
                    int min = Math.min(replace, Math.min(add, remove));
                    dp[i][j] = 1 + min;
                }
            }
        }
        System.out.println(dp[n-1][m-1]);
    }
}