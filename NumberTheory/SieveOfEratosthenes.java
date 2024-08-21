package DPBootcamp.NumberTheory;

import java.util.Arrays;
import java.util.Scanner;

public class SieveOfEratosthenes {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] sieve = new boolean[n + 1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;

        for(int i = 2; i <= n; i++){
            if(sieve[i]){ //if current element is prime
                for(int j = i * i; j <= n; j += i) //mark all its multiples starting from i * i
                    sieve[j] = false;
            }
        }
        for(int i = 2; i <= n; i++){
            if(sieve[i])
                System.out.print(i+" ");
        }
        System.out.println();
    }
}
