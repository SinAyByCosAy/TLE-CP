package DPBootcamp.NumberTheory;

import java.util.Arrays;
import java.util.Scanner;

//Given Q queries (1<=Q<=10^6), every query with a number N (1<=N<=10^6)
//find number of factors of N
//find sum of factors of N
public class NoAndSumOfFactorsWithQueries {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int limit = (int) 1e6;
        int[] spf = getSPFList(limit);
        int[] countOfFactors = countFactorsList(limit);
        int[] sumOfFactors = sumFactorsList(limit);
        int q = sc.nextInt();
        while(q-- > 0){
            int n = sc.nextInt();
            System.out.println(n);
            //Method 1 takes O(NlogN + Q) time
            System.out.println("Method: pre-computed answer via optimized loops");
            System.out.println("Count of factors: "+countOfFactors[n]);
            System.out.println("Sum of factors: "+sumOfFactors[n]);

            //Method 2 takes O(Nlog(logN) ~ O(N) + O(QlogN) time
            System.out.println("Method: SPF");
            System.out.println("Count of factors: " + spfFactorsCount(n, spf));
            System.out.println("Sum of factors: " + spfFactorsSum(n, spf));
        }
    }
    //Method 2 works better if Q is smaller, else both are fine
    public static int spfFactorsCount(int n, int[] spf){
        int ans = 1;
        while(n != 1){
            int factor = spf[n];
            int count = 0;
            while(n % factor == 0){
                n /= factor;
                count++;
            }
            ans *= (count + 1);
        }
        return ans;
    }
    public static int spfFactorsSum(int n, int[] spf){
        int ans = 1;
        while(n != 1){
            int factor = spf[n];
            int count = 0;
            while(n % factor == 0){
                n /= factor;
                count++;
            }
            ans *= ((int)Math.pow(factor, count + 1) - 1) / (factor - 1);
        }
        return ans;
    }
    public static int[] getSPFList(int limit){
        boolean[] sieve = new boolean[limit + 1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;
        int[] spf = new int[limit + 1];
        for(int i = 1; i <= limit; i++)
            spf[i] = i;

        for(int i = 2; i <= (int)1e4; i++){
            if(sieve[i]){
                for(int j = i * i; j <= limit; j += i){
                    sieve[j] = false;
                    spf[j] = Math.min(spf[j], i);
                }
            }
        }
        return spf;
    }

    public static int[] countFactorsList(int limit){
        int[] countOfFactors = new int[limit + 1];
        Arrays.fill(countOfFactors, 1);
        for(int i = 2; i <= limit; i++){
            for(int j = i; j <= limit; j += i)
                countOfFactors[j]++;
        }
        return countOfFactors;
    }
    public static int[] sumFactorsList(int limit){
        int[] sumOfFactors = new int[limit + 1];
        Arrays.fill(sumOfFactors, 1);
        for(int i = 2; i <= limit; i++){
            for(int j = i; j <= limit; j += i)
                sumOfFactors[j] += i;
        }
        return sumOfFactors;
    }
}
