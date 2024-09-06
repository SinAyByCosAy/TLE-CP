package DPBootcamp.NumberTheory;

import java.util.Scanner;

//Binary Exponentiation
public class ApowerN {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int n = sc.nextInt();
        //TC : O(log(N))
        System.out.println(recurAPowerN(a, n));
        System.out.println(vanillaAPowerN(a, n));
    }
    public static long recurAPowerN(int a, int n){
        if(n == 0)
            return 1;

        long res = recurAPowerN(a, n / 2);
        if(n % 2 == 1)
            res *= 1l * res * a;
        else
            res *= 1l * res;

        return res;
    }

    public static long vanillaAPowerN(int a, int n){
        long res = 1;
        while(n >  0){
            if((n & 1) == 1){
                res *= 1l * a;
            }
            a *= a;
            n >>= 1;
        }
        return res;
    }
}
