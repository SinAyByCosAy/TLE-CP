//Given a list of points on a 2D plane, rearrange these points in any way such that
// in the final permutation of points, the sum of distances of the adjacent elements is minimized.
//Points : [{0, 0}, {5, 6}, {1, 2}
//Best permutation →> [{0, 0}, {1, 2}, {5, 6}]
//Ans = Dist(P1, P3) + Dist(P3, P2)

//At every position we can have all the points. Therefore we need all the possible permutations.
//We use DP with bitmasking to generate all permutations and we also need to keep track of the point picked at the previous index
//because we need to calculate distance with the previous point.

//State : dp[idx][mask][lastIdx]
package DPBootcamp.DP;

import java.util.Scanner;

public class TwoDPlanePointsRearrange {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++) { arr[i][0] = sc.nextInt(); arr[i][1] = sc.nextInt(); }
        int pow = (1 << n);
        int[][] dp = new int[pow][n];
        for(int i = 0; i < pow; i++) for(int j = 0; j < n; j++)dp[i][j] = -1;
        System.out.println(getMinDistance(arr, dp, 0, 0));
    }
    public static int getMinDistance(int[][] arr, int[][] dp, int mask, int lastIdx){
        int curr = countSetBits(mask);
        if(curr == arr.length) return 0;
        if(dp[mask][lastIdx] != -1) return dp[mask][lastIdx];

        int dist = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            if((mask & (1 << i)) == 0)
                dist = Math.min(dist, (curr == 0 ? 0 : calculateDist(arr, i, lastIdx)) +
                        getMinDistance(arr, dp, (mask | (1 << i)), i));
        }
        dp[mask][lastIdx] = dist;
        return dist;
    }
    public static int calculateDist(int[][] arr, int curr, int prev){
        return Math.abs(arr[curr][0] - arr[prev][0]) + Math.abs(arr[curr][1] - arr[prev][1]);
    }
    public static int countSetBits(int mask){
        int count = 0;
        while(mask > 0){
            if((mask & 1) == 1) count++;
            mask >>= 1;
        }
        return count;
    }
}
//TC: O(N^2 . 2^N)