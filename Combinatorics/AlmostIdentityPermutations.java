package DPBootcamp.Combinatorics;

import java.util.Scanner;

//solution => r from (n-k) to (n-2) : summation [nCr * D(n-r)], then total + 1
//D - Derangement
//since k<=4, we need max 4 terms for nCr and Dx calculation, otherwise overflow
public class AlmostIdentityPermutations {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int req = n - k;
        long ans = 0;
        int[] fact = new int[5];
        fact[0] = 1;
        for(int i = 1; i <= 4; i++)
            fact[i] = fact[i - 1] * i;
        for(int i = req; i <= n - 2; i++){
            ans += nCr(n, i) * derangement (n - i, fact);
        }
        System.out.println(ans + 1);
    }
    public static long nCr(int n, int r){
        long ans = 1;
        for(int i = 0; i < n - r; i++){
            ans *= (n - i);
            ans /= (i + 1);
        }
        return ans;
    }
    public static long derangement(int x, int[] fact){
        long ans = 0;
        long sign = 1;
        for(int i = 2; i <= x; i++) {
            ans += sign * (fact[x] / fact[i]);
            sign *= -1;
        }
        return ans;
    }
}
//TC:O(K)