//https://cses.fi/problemset/task/1716
package DPBootcamp.Combinatorics;

import java.util.Scanner;

//(m+n-1)Cm or (m+n-1)C(n-1)
public class DsitributingApples {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt(); //identical items
        int mod = (int) 1e9 + 7;
        int numerator = 1, denominator = 1;
        for(int i = 1; i <= m + n - 1; i++){
            numerator = modMul(numerator, i, mod); //numerator % mod
            if(i == m) denominator = modMul(denominator, mmInvPrime(numerator, mod), mod); //[m!]^-1
            if(i == n - 1) denominator = modMul(denominator, mmInvPrime(numerator, mod), mod); //[m!]^-1 * [(n-1)!]^-1
        }
        System.out.println(modMul(numerator, denominator, mod)); //(m+n-1)! * [m!]^-1 * [(n-1)!]^-1
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
//TC: O(m + n)