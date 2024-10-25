//https://codeforces.com/problemset/problem/1284/C
package DPBootcamp.Combinatorics;

import java.util.Scanner;

//solution = for i from 0 to n-1, summation of [ (n-i) * (i+1)! * (n-(i+1))! * (n-i) ]
public class NewYearPermutation {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] factorial = getFactorial(n, m);
        long ans = 0;
        for(int i = 0; i < n; i++){
            int res = modMul(modMul(modMul(n - i, factorial[i + 1], m), factorial[n - (i + 1)], m), n - i, m);
            ans = (ans + res) % m;
        }
        System.out.println(ans);
    }
    public static int[] getFactorial(int n, int mod){
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for(int i = 1; i <= n; i++)
            factorial[i] = modMul(factorial[i - 1], i, mod);
        return factorial;
    }
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
}
//TC: O(N)