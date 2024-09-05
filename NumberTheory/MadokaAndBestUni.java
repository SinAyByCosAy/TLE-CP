//https://codeforces.com/problemset/problem/1717/E
//2200 rating, just requires mathematical observations
package DPBootcamp.NumberTheory;

import java.util.Scanner;

public class MadokaAndBestUni {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] phiValues = getPhiValues(n);
        int mod = 1000 * 1000 * 1000 + 7;
        long res = 0;
        for(int c = 1; c <= n - 2; c++){//iterating over c values
            //O(N)
            int rem = n - c;
            for(int d = 1; d * d <= rem; d++){//iterating over factors(d) of (n - c)
                //O(sqrt(N))
                if(rem % d == 0){//found a factor, actually 2 factors
                    res += phiValues[rem / d] * lcm(c, d);
                    res %= mod;

                    if(rem / d != d && d > 1){//second factor
                        res += phiValues[rem / (rem / d)] * lcm(c, (rem / d));
                        res %= mod;
                    }
                    //lcm -> O(logN)
                }
            }
        }
        System.out.println(res);
    }
    public static int[] getPhiValues(int n){
        int[] phiValues = new int[n + 1];
        for(int i = 1; i <= n; i++)
            phiValues[i] = i;
        for(int i = 2; i <= n; i++){
            if(phiValues[i] == i){//found a prime
                for(int j = i; j <= n; j += i)
                    phiValues[j] -= phiValues[j] / i;
            }
        }
        return phiValues;
    }
    public static long lcm(int a, int b){
        return 1l * a * b / gcd(a, b);
    }
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
//TC: O(N * Sqrt(N) * logN)