//https://leetcode.com/problems/right-triangles/
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class RightTriangles {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(numberOfRightTriangles(arr));
    }
    public static long numberOfRightTriangles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] row = new int[n];
        int[] col = new int[m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        long ans = 0;
        //treating every 1 as the right-angled vertex of the triangle
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1)
                    ans += ((row[i] - 1) * 1l * (col[j] - 1));
            }
        }
        return ans;
    }
}
//TC:O(N.M)