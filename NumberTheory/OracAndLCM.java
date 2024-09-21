package DPBootcamp.NumberTheory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OracAndLCM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int limit = (int) 3e5;
        int[] spf = getSPFArray(limit);
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        ArrayList<Integer>[] primePower = new ArrayList[(int) 2e5 + 1];
        for(int i = 0; i <= (int) 2e5; i++)
            primePower[i] = new ArrayList<>();

        updatePrimeFactors(arr, primePower, spf);

        System.out.println(getGCD(primePower, n));
    }
    public static long getGCD(ArrayList<Integer>[] primePower, int n){
        long gcd = 1;
        for(int i = 0; i <= (int) 2e5; i++){
            int len = primePower[i].size();
            if(len < n - 1) continue;
            else{
                Collections.sort(primePower[i]);
                if(len == n - 1) gcd *= 1l * binaryExpo(i, primePower[i].get(0));
                else gcd *= 1l * binaryExpo(i, primePower[i].get(1));
            }
        }
        return gcd;
    }
    public static void updatePrimeFactors(int[] arr, ArrayList<Integer>[] primePower, int[] spf){
        for(int x : arr){
            while(x > 1){
                int factor = spf[x];
                int count = 0;
                while(x % factor == 0){
                    x /= factor;
                    count++;
                }
                primePower[factor].add(count);
            }
        }
    }
    public static int[] getSPFArray(int limit){
        int[] spf = new int[limit + 1];
        for(int i = 1; i <= limit; i++)
            spf[i] = i;
        for(int i = 2; i <= (int)1e3; i++){
            if(spf[i] == i){
                for(int j = i * i; j <= limit; j += i)
                    spf[j] = Math.min(spf[j], i);
            }
        }
        return spf;
    }
    public static int binaryExpo(int a, int b){
        int res = 1;
        while(b > 0){
            if((b & 1) == 1)
                res *= a;
            a *= a;
            b >>= 1;
        }
        return res;
    }
}