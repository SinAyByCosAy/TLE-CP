//https://leetcode.com/problems/unique-paths/
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class UniquePaths {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        System.out.println(getCombinations(m + n - 2, Math.min(m - 1, n - 1)));
    }
    public static int getCombinations(int n, int r){
        long ans = 1;
        for(int i = 0; i < r; i++){
            ans *= (n - i); //numerator : n * (n - 1) * (n - 2) .... (n - r + 1)
            ans /= (i + 1); //denominator : 1 * 2 * 3 .... r
        }
        return (int) ans;
    }
}
//TC: O(Min(n, m))