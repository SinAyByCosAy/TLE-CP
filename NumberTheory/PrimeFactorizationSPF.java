package DPBootcamp.NumberTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PrimeFactorizationSPF {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int limit = (int)1e6;
        boolean[] sieve = new boolean[limit + 1];
        int[] spf = new int[limit + 1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;
        for(int i = 1; i <= limit; i++)
            spf[i] = i;

        //Sieve and SPF pre-computation, TC: O(N * log(log(N)))
        for(int i = 2; i <= (int)1e4; i++){
            if(sieve[i]){
                for (int j = i * i; j <= limit; j += i) {
                    sieve[j] = false;
                    spf[j] = Math.min(spf[j], i);
                }
            }
        }
        ArrayList<Prime> list = getPrimeFactorization(n, spf);
        for(Prime pair : list){
            System.out.println(pair.factor+" ^ "+pair.power);
        }
    }
    public static ArrayList<Prime> getPrimeFactorization(int n, int[] spf){
        ArrayList<Prime> list = new ArrayList<>();
        //getting SPF for every X -> O(1)
        //Total divisions possible by all factors -> <= log N
        //TC: O(log N)
        while(n != 1){
            int count = 0;
            int factor = spf[n];
            while(n % factor == 0){
                n /= factor;
                count++;
            }
            list.add(new Prime(factor, count));
        }
        return list;
    }
}
