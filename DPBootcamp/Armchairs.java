//https://codeforces.com/problemset/problem/1525/D
package DPBootcamp.DPBootcamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Armchairs {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> seated = new ArrayList<>();
        ArrayList<Integer> emptySeats = new ArrayList<>();
        seated.add(0);
        emptySeats.add(0);
        for(int i=1;i<=n;i++){
            int x = sc.nextInt();
            if(x == 1)
                seated.add(i);
            else
                emptySeats.add(i);
        }
        int n1 = seated.size() - 1;
        int n2 = emptySeats.size() - 1;
        long dp[][] = new long[n1+1][n2+1];
        for(int i=1;i<=n1;i++){
            for(int j=0;j<=n2;j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i=1;i<=n1;i++){
            for(int j=1;j<=n2;j++){
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + Math.abs(seated.get(i) - emptySeats.get(j)));
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
            }
        }
        System.out.println(dp[n1][n2]);
    }
}
