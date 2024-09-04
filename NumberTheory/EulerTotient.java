package DPBootcamp.NumberTheory;

import java.util.Arrays;
import java.util.Scanner;

public class EulerTotient {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(phi(n));
        System.out.println(Arrays.toString(phiOneToN(n)));
    }
    public static int phi(int n){//can be also done using SPF
        //TC: O(sqrt(N))
        int result = n;
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                result -= result / i;
                while(n % i == 0)
                    n /= i;
            }
        }
        if(n > 1)
            result -= result / n;

        return result;
    }

    public static int[] phiOneToN(int n){
        //similar to sieve or SPF construction logic, TC: << O(NlogN)
        int[] phiValues = new int[n + 1];
        for(int i = 1; i <= n; i++)
            phiValues[i] = i;

        for(int i = 2; i <= n; i++){
            if(phiValues[i] == i){//found a prime
                for(int j = i; j <= n; j += i)//contribution to all the multiples of prime
                    phiValues[j] -= phiValues[j] / i;
            }
        }
        return phiValues;
    }
}