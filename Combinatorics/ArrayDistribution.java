//https://codeforces.com/problemset/problem/57/C
package DPBootcamp.Combinatorics;

import java.util.Scanner;

//Think in terms of freq distribution of 1 to n
//eqn => x1 + x2 + x3 + ..... xn = n , where xi >= 0
//find all possible solutions to this equation

//Soln = [(2n-1)Cn * 2] - n
public class ArrayDistribution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int mod = (int) 1e9 + 7;
        int numerator = 1, denominator = 1;
        for(int i = 1; i <= 2*n - 1; i++){
            numerator = modMul(numerator, i, mod);
            if(i == n) denominator = modMul(denominator, mmInvPrime(numerator, mod), mod);
            if(i == n - 1) denominator = modMul(denominator, mmInvPrime(numerator, mod), mod);
        }
        int result = modMul(numerator, denominator, mod); //(2n-1)Cn
        result = modMul(result, 2, mod); //(2n-1)Cn * 2
        result = (result - n + mod) % mod; //(2n-1)Cn * 2 - n
        System.out.println(result);
    }
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
    public static int mmInvPrime(int a, int m){
        int b = m - 2;
        int res = 1;
        while(b > 0){
            if((b & 1) == 1) res = modMul(res, a, m);
            a = modMul(a, a, m);
            b >>= 1;
        }
        return res;
    }
}