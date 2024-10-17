//https://cses.fi/problemset/task/1717/
package DPBootcamp.Combinatorics;

import java.util.Scanner;

//Dn = n! * [1/2! - 1/3! + 1/4! - 1/5! + ....... (-1)^n / n!]
public class ChristmasParty {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n == 1){
            System.out.println(0);
            System.exit(0);
        }
        int fact = 1;
        int mod = (int) 1e9 + 7;
        for(int i = 1; i <= n; i++)
            fact = modMul(fact, i, mod); //calculating n!
        int[] invFactorial = getInvFactorial(n, fact, mod); //pre-computing all inv. factorials
        long combinations = 0; //long because we need to add "mod" due to subtraction
        int sign = 1;
        for(int i = 2; i <= n; i++){
            int term = modMul(fact, invFactorial[i], mod); // n! / i!
            term *= sign;
            combinations = (combinations + term + mod) % mod; //adding to the total
            sign *= -1;
        }
        System.out.println(combinations);
    }
    public static int[] getInvFactorial(int n, int fact, int mod){
        int[] invFactorial = new int[n + 1];
        invFactorial[n] = mmInvPrime(fact, mod);
        for(int i = n - 1; i >= 0; i--)
            invFactorial[i] = modMul(invFactorial[i + 1], i + 1, mod);
        return invFactorial;
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
        return (int) ((x * 1l * y) % mod);
    }
}
//TC: O(N)