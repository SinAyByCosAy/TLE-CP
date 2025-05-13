//https://cses.fi/problemset/task/2181/
package DPBootcamp.DP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountingTilings {
    static int mod = (int)1e9 + 7;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int totalPermutations = (1 << n);
        List<Integer>[] transitions = new ArrayList[totalPermutations];
        for(int i = 0; i < totalPermutations; i++) transitions[i] = new ArrayList<>();
        int[][] dp = new int[m][totalPermutations];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < totalPermutations; j++){
                dp[i][j] = -1;
            }
        }
        generateTransitions(transitions, 0, 0, 0, n);
        System.out.println(getTotalWays(dp, transitions, 0, 0, m));
    }
    public static int getTotalWays(int[][] dp, List<Integer>[] transitions, int colIdx, int mask, int m){//O(M.2^N)
        if(colIdx == m) return (mask == 0) ? 1 : 0; //all tiles placed till, if there is no tile crossing the boundary(mask == 0), it's a successful tiling
        if(dp[colIdx][mask] != -1) return dp[colIdx][mask];

        int ans = 0;
        //get all possible ways the next column gets affected by placing tiles in the current column
        for(int nextMask : transitions[mask]) ans = (ans + getTotalWays(dp, transitions, colIdx + 1, nextMask, m)) % mod;

        return dp[colIdx][mask] = ans;
    }
    //we have to generate all permutations of original mask, every bit has two option: 0 or 1, #masks = 2 ^ n
    public static void generateTransitions(List<Integer>[] transitions, int rowIdx, int mask1, int mask2, int n){//O(3 ^ N)
        if(rowIdx > n) return;
        if(rowIdx == n){
            transitions[mask1].add(mask2);
            return;
        }
        //take current bit as 1, therefore it's blocked. The next mask at current index will also remain empty
        generateTransitions(transitions, rowIdx + 1, mask1 + (1 << rowIdx), mask2, n);

        //take current bit as 0, we have two options. Vertical and horizontal tiling
        generateTransitions(transitions, rowIdx + 1, mask1, mask2 + (1 << rowIdx), n);
        generateTransitions(transitions,rowIdx + 2, mask1, mask2, n);
    }
}
//TC: O(M.2^N)