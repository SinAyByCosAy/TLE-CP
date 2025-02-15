//https://codeforces.com/contest/2065/problem/G
package DPBootcamp.Contests.CF1003Div4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProbG {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int limit = (int) 2e5 + 1;
        Pair[] factors = new Pair[limit];
        for(int i = 2; i < limit; i++) factors[i] = new Pair();
        //pre compute primes, semi primes and store factor info
        for(int i = 2; i < limit; i++){
            if(factors[i].prime) factors[i].semiPrime = false;
            for(int j = i + i; j < limit; j += i){
                factors[j].prime = false;
                factors[j].count++;
                if(!factors[i].prime || factors[j].count > 2) factors[j].semiPrime = false;
                if(factors[j].factor1 == 0) factors[j].factor1 = i;
                else factors[j].factor2 = i;
            }
        }
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
            HashMap<Integer, Integer> primes = new HashMap<>();
            HashMap<Integer, Integer> semiPrimes = new HashMap<>();
            int countPrime = 0;
            for(int i = 0; i < n; i++) {
                if (factors[arr[i]].prime) {
                    primes.put(arr[i], primes.getOrDefault(arr[i], 0) + 1);
                    countPrime++;
                } else if (factors[arr[i]].semiPrime) semiPrimes.put(arr[i], semiPrimes.getOrDefault(arr[i], 0) + 1 );
            }
            long ans = 0;
            for(Map.Entry<Integer, Integer> prime : primes.entrySet()){
                int val = prime.getValue();
                countPrime -= val;
                ans += 1l * val * countPrime;
            }
            for(Map.Entry<Integer, Integer> semiPrime : semiPrimes.entrySet()){
                int sp = semiPrime.getKey();
                int count = semiPrime.getValue();
                ans += count;
                if(primes.containsKey(factors[sp].factor1)) ans += 1l * count * primes.get(factors[sp].factor1);
                if(primes.containsKey(factors[sp].factor2)) ans += 1l * count * primes.get(factors[sp].factor2);
                if(count > 1) ans += nCr(count, 2);
            }
            System.out.println(ans);
        }
    }
    public static long nCr(int n, int r){
        long ans = 1;
        for(int i = 0; i < r; i++){
            ans *= (n - i); //numerator : n * (n - 1) * (n - 2) .... (n - r + 1)
            ans /= (i + 1); //denominator : 1 * 2 * 3 .... r
        }
        return ans;
    }
    static class Pair{
        boolean prime, semiPrime;
        int count, factor1, factor2;
        Pair(){
            prime = true;
            semiPrime = true;
            count = 0;
            factor1 = 0;
            factor2 = 0;
        }
    }
}
//TC: O(NlogN)