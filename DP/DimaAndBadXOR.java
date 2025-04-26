//https://codeforces.com/contest/1151/problem/B
package DPBootcamp.DP;

import java.util.Scanner;

public class DimaAndBadXOR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = sc.nextInt();

        Pair[][] dp = new Pair[n + 1][1024];
        for(int i = 0; i <= n; i++)
            for(int j = 0; j < 1024; j++)
                dp[i][j] = new Pair(-1, -1);

        Pair res = getXORPath(dp, arr, 0, 0, n, m);
        if(res.check == 0) System.out.println("NIE");
        else{
            System.out.println("TAK");
            int currXor = 0;
            for(int i = 0; i < n; i++) {
                int ele = dp[i][currXor].pos;
                System.out.print((ele + 1) + " ");
                currXor ^= arr[i][ele];
            }
        }
    }
    public static Pair getXORPath(Pair[][] dp, int[][] arr, int i, int xor, int n, int m){
        if(i == n) return new Pair((xor > 0) ? 1 : 0, -1);
        if(dp[i][xor].check != -1) return dp[i][xor];

        dp[i][xor].check = 0;
        for(int j = 0; j < m; j++){
            if(getXORPath(dp, arr, i + 1, arr[i][j] ^ xor, n, m).check == 1){
                dp[i][xor].check = 1;
                dp[i][xor].pos = j;
                break;
            }
        }
        return dp[i][xor];
    }
}
class Pair{
    int check, pos;
    Pair(int check, int pos){
        this.check = check;
        this.pos = pos;
    }
}
//TC: O(N . M . 10^3)
//TC: O(500 . 500 . 10^3)
//in reality, Xors can only be N for each row and not 10^3, therefore, TC will be less by a factor of 10