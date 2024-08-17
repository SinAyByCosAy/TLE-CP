package DPBootcamp.NumberTheory;

import java.util.ArrayList;
import java.util.Scanner;

public class Factors {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> factors = new ArrayList<>();
        for(int p = 1; p * p <= n; p++){
            if(n % p == 0){
                factors.add(p);
                if(n / p != p)
                    factors.add(n / p);
            }
        }
        System.out.println(factors);
    }
}
