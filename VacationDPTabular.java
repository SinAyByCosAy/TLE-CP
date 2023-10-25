//https://atcoder.jp/contests/dp/tasks/dp_c
package DPBootcamp;

import java.util.Scanner;

public class VacationDPTabular {
    static int a[][], dp[][];
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new int[n + 1][4];
        for (int i = 1; i <= n; i++) {
            a[i][1] = sc.nextInt();
            a[i][2] = sc.nextInt();
            a[i][3] = sc.nextInt();
        }
        dp = new int[n + 1][4];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=3;j++){
                int ans = -1;
                for(int prev=1;prev<=3;prev++){
                    if(prev == j)
                        continue;
                    ans = Math.max(ans, a[i][j] + dp[i-1][prev]);
                }
                dp[i][j] = ans;
            }
        }
        int ans = Math.max(dp[n][1], Math.max(dp[n][2], dp[n][3]));
        System.out.println(ans);
    }
}
