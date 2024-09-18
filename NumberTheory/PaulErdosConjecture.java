//https://www.spoj.com/problems/HS08PAUL/
package DPBootcamp.NumberTheory;

import java.util.Arrays;
import java.util.Scanner;

public class PaulErdosConjecture {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int limit = (int) 1e7;
        boolean[] sieve = getSieve(limit);//~O(N)
        boolean[] conjectureArray = getConjectureArray(sieve, limit);//O(N ^ (3/4)) ~ O(N)
        int[] conjecturePS = new int[limit + 1];
        for(int i = 1; i <= limit; i++){//O(N)
            //if a number is prime and it is at the conjecture position, it is a candidate element
            if(sieve[i] && conjectureArray[i]) conjecturePS[i] = conjecturePS[i - 1] + 1;
            else conjecturePS[i] = conjecturePS[i - 1];
        }
        while(t-- > 0){
            int n = sc.nextInt();
            System.out.println(conjecturePS[n]);
        }
    }
    //pre-computing the possible x^2 + y^4 positions
    public static boolean[] getConjectureArray(boolean[] sieve, int limit){
        boolean[] conjectureArray = new boolean[limit + 1];
        for(int i = 1; i * i <= limit; i++){
            for(int j = 1; (i * i) + (j * j * j * j) <= limit; j++){
                conjectureArray[i*i + j*j*j*j] = true;
            }
        }
        return conjectureArray;
    }
    public static boolean[] getSieve(int limit){
        boolean[] sieve = new boolean[limit+1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;
        for(int i = 2; i <= (int) 1e4; i++){
            if(sieve[i]){
                for(int j = i * i; j <= limit; j += i)
                    sieve[j] = false;
            }
        }
        return sieve;
    }
}
//TC: O(T . N)