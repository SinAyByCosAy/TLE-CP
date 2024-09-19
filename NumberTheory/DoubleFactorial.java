//https://atcoder.jp/contests/abc148/tasks/abc148_e
package DPBootcamp.NumberTheory;

import java.util.Scanner;

//#trailing zeros is number of 2 * 5 possible combinations
//not possible for odd values
// for even, all numbers have 2, every 5th number has 5, every 25th number has 5*5 .....
public class DoubleFactorial {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        if(n % 2 == 1) System.out.println(0);
        else{
            long count = 0;
            n = n / 2;
            for(long i = 5; i <= n; i *= 5){
                count += n / i;
            }
            System.out.println(count);
        }
    }
}
