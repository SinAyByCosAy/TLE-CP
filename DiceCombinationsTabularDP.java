//https://cses.fi/problemset/task/1633/
package DPBootcamp;

import java.util.Scanner;

public class DiceCombinationsTabularDP {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = 1000 * 1000 * 1000 + 7;
        int dp[] = new int[n+1];
        dp[0] = 1;
        for(int i=1;i<=n;i++){
            long count = 0;
            for(int j=1;j<=6;j++){
                if(i-j<0)
                    break;
                count = (count + dp[i-j]) % m;
            }
            dp[i] = (int)count;
        }
        System.out.println(dp[n]);
    }
}
//TC: O(N)
//SC: O(N)