//https://atcoder.jp/contests/dp/tasks/dp_c
package DPBootcamp.DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class Vacation {
    static int a[][], dp[][];
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new int[n+1][4];
        for(int i=1;i<=n;i++){
            a[i][1] = sc.nextInt();
            a[i][2] = sc.nextInt();
            a[i][3] = sc.nextInt();
        }
        dp = new int[n+1][4];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        maxHappiness(n, 1);
        maxHappiness(n, 2);
        maxHappiness(n, 3);
        int ans = Math.max(dp[n][1], Math.max(dp[n][2], dp[n][3]));
        System.out.println(ans);
    }
    static int maxHappiness(int day, int activity){//max happiness that can be achieved by doing activity j
        if(day == 0)
            return 0;
        if(dp[day][activity] != -1)
            return dp[day][activity];
        int ans = -1;
        for(int i=1;i<=3;i++){
            if(i==activity) continue;
            ans = Math.max(ans, a[day][activity] + maxHappiness(day-1, i));
        }
        dp[day][activity] = ans;
        return dp[day][activity];
    }
}
