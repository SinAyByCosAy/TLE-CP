//https://codeforces.com/contest/1475/problem/E
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class AdvertisingAgency {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int mod = (int) 1e9 + 7;
        int[] factorial = getFactorial(1000, mod);
        int[] inverseFact = getInverseFactorial(1000, factorial, mod);
        while(t-- > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] freq = new int[1001]; //freq map of 1-1000 values to fetch count in O(1)
            for(int i = 0; i < n; i++)
                freq[sc.nextInt()]++;
            int ans = 1;
            for(int i = 1000; i >= 0; i--){
                if(k >= freq[i])
                    k -= freq[i];
                else {
                    ans = modNCR(freq[i], k, mod, factorial, inverseFact);
                    break;
                }
            }
            System.out.println(ans);
        }
    }
    public static int modNCR(int n, int r, int mod, int[] factorial, int[] inverseFact){
        return modMul(modMul(factorial[n], inverseFact[r], mod), inverseFact[n - r], mod);
    }
    public static int[] getFactorial(int limit, int mod){
        int[] factorial = new int[limit + 1];
        factorial[0] = 1;
        for(int i = 1; i <= limit; i++)
            factorial[i] = modMul(factorial[i - 1], i, mod);
        return factorial;
    }
    public static int[] getInverseFactorial(int limit, int[] fact, int mod){
        int[] inverseFact = new int[limit + 1];
        inverseFact[limit] = mmInvPrime(fact[limit], mod);
        for(int i = limit - 1; i >= 0; i--)
            inverseFact[i] = modMul(inverseFact[i + 1], i + 1, mod);
        return inverseFact;
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
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
}
//TC: O(1000)