package DPBootcamp.Contests.LCWeekly438;

import java.util.Arrays;
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        }
        int l = sc.nextInt();
        int[] limits = new int[l];
        for(int i = 0; i < l; i++) limits[i] = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(maxSum(grid, limits, k));
    }
    public static long maxSum(int[][] grid, int[] limits, int k) {
        int n = grid.length;
        int m = grid[0].length;
        sortRowsDescending(grid);
        int[] pointers = new int[n];
        boolean stop = false;
        long sum = 0;
        int count = 0;
        while(!stop){
            int row = -1;
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++){
                if(pointers[i] < limits[i]){
                    if(grid[i][pointers[i]] > max){
                        max = grid[i][pointers[i]];
                        row = i;
                    }
                }
            }
            if(max == Integer.MIN_VALUE) break;
            sum += max;
            pointers[row]++;
            count++;
            if(count == k) stop = true;
        }
        return sum;
    }
    public static void sortRowsDescending(int[][] arr) {
        for (int[] row : arr) {
            // Sort in ascending order
            Arrays.sort(row);
            // Reverse the sorted array
            reverseArray(row);
        }
    }

    // Helper method to reverse an array
    private static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
