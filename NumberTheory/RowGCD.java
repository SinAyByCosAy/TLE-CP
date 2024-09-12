//https://codeforces.com/problemset/problem/1458/A
package DPBootcamp.NumberTheory;

import java.util.Scanner;

//gcd(a, b, c) = gcd(a, b-a, c-a)
public class RowGCD {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] a = new long[n];
        long[] b = new long[m];
        for(int i = 0; i < n; i++)
            a[i] = sc.nextLong();
        for(int i = 0; i < m; i++)
            b[i] = sc.nextLong();

        //gcd(a[0] + b[j],...,a[n-1] + b[j]) = gcd(a[0]+b[j], |a[1]-a[0]|,...,|a[n]-a[0]|)
        //except a[0]+b[j], everything can be pre-computed
        long calculatedGCD = 0;
        for(int i = 1; i < n; i++)//O(N)
            calculatedGCD = gcd(calculatedGCD, Math.abs(a[i] - a[i - 1]));

        for(long val : b){//O(M)
            System.out.print(gcd(a[0] + val, calculatedGCD) + " ");
        }
    }
    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a % b);
    }
}

//TC: O(N + M*log(A))