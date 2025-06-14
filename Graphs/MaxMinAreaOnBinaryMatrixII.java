package DPBootcamp.Graphs;

import java.util.Scanner;

//given a (n * n) binary matrix, find the max and min areas of an island.
//0: water, 1: land
public class MaxMinAreaOnBinaryMatrixII {
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //preferred strategy to move in other directions, can be used to scale to more directions easily
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++) for(int j = 0; j < n; j++) matrix[i][j] = sc.nextInt();
        boolean[][] visited = new boolean[n][n];
        int maxArea = 0;
        int minArea = n * n;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1 && !visited[i][j]){
                    int area = dfs(matrix, visited, i, j, n);
                    maxArea = Math.max(maxArea, area);
                    minArea = Math.min(minArea, area);
                }
            }
        }
        System.out.println(maxArea);
        System.out.println(maxArea == 0 ? 0 : minArea);
    }
    public static int dfs(int[][] matrix, boolean[][] visited, int i, int j, int n){
        if(i < 0 || j < 0 || i >= n || j >= n || matrix[i][j] == 0 || visited[i][j]) return 0; //invalid node

        visited[i][j] = true;
        int count = 1;
        for(int[] dx : dir)
            count += dfs(matrix, visited, i + dx[0], j + dx[1], n); //better way to move to each neighbor

        return count;
    }
}
