package DPBootcamp.NumberTheory;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimeFactorization {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Prime> list = primeFactorization(n);
        for(Prime pair : list){
            System.out.println(pair.factor+" ^ " + pair.power);
        }
    }
    public static ArrayList<Prime> primeFactorization(int n){
        ArrayList<Prime> list = new ArrayList<>();
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0) {//found a factor
                int count = 0;
                while (n % i == 0) {//divide to get all the powers
                    n /= i;
                    count++;
                }
                list.add(new Prime(i, count));//add
            }
        }
        if(n > 1) list.add(new Prime(n, 1));//factor > sqrt(N) present
        return list;
    }
}
class Prime{
    int factor, power;
    Prime(int factor, int power){
        this.factor = factor;
        this.power = power;
    }
}
