//Find nCr % 1e9+7
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class BinomialCoeffWithMod {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int mod = (int) 1e9 + 7;
        int[] factorial = getFactorial((int)1e6, mod); //pre-computed factorials
        int[] inverseFactorial = getInverseFactorial((int)1e6, factorial, mod); //pre-computed inverse factorials
        int q = sc.nextInt();
        while(q-- > 0){
            int n = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(modMul(modMul(factorial[n], inverseFactorial[r], mod), inverseFactorial[n - r], mod));
        }
    }
    public static int[] getFactorial(int limit, int mod){
        int[] factorial = new int[limit + 1];
        factorial[0] = 1;
        for(int i = 1; i <= limit; i++){ //O(N)
            factorial[i] = modMul(factorial[i - 1], i, mod);
        }
        return factorial;
    }
    public static int[] getInverseFactorial(int limit, int[] factorial, int mod){
        int[] inverseFactorial = new int[limit + 1];
        inverseFactorial[limit] = mmInvPrime(factorial[limit], mod); //O(log m)
        for(int i = limit - 1; i >= 0; i--){ //O(N)
            inverseFactorial[i] = modMul(inverseFactorial[i + 1], i + 1, mod);
        }
        return inverseFactorial;
    }
    public static int modMul(int x, int y, int m){
        return (int)((x * 1l * y) % m);
    }

    //multiplicative modulo inverse under a prime: (x ^ (m-2)) % m
    public static int mmInvPrime(int x, int m){
        int y = m - 2;
        int ans = 1;
        while(y > 0){
            if((y & 1) == 1) ans = modMul(ans, x, m);
            x = modMul(x, x, m);
            y = y >> 1;
        }
        return ans;
    }
}
