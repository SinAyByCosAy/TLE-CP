//https://atcoder.jp/contests/dp/tasks/dp_f
package DPBootcamp.DP;

import java.util.Scanner;

public class LCS {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            char ch_s = s.charAt(i);
            for(int j = 0; j < m; j++){
                char ch_t = t.charAt(j);
                if(ch_s == ch_t){
                    dp[i][j] = 1 + (i == 0 || j == 0 ? 0 : dp[i - 1][j - 1]);
                }else{
                    dp[i][j] = Math.max(
                            i == 0 ? 0 : dp[i - 1][j],
                            j == 0 ? 0 : dp[i][j - 1]
                    );
                }
            }
        }
        StringBuilder result = new StringBuilder();
        int i = n - 1, j = m - 1;
        while(i >=0 && j >= 0){
            char ch_s = s.charAt(i);
            char ch_t = t.charAt(j);
            if(ch_s == ch_t){
                result.insert(0, ch_s);
                i--;
                j--;
            }else{
                if(j > 0 && dp[i][j] == dp[i][j - 1]) j--;
                else if(i > 0 && dp[i][j] == dp[i - 1][j]) i--;
                else break;
            }
        }
        System.out.println(result.toString());
    }
}
//TC: O(N.M)