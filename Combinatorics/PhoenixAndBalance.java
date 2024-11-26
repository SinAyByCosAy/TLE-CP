//https://codeforces.com/problemset/problem/1348/A
package DPBootcamp.Combinatorics;

import java.util.Scanner;

//we pair the smallest nos. with 2^n in one group and remaining in the other group
public class PhoenixAndBalance {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long[] powers = new long[31];
        long power = 1;
        for(int i = 1; i <= 30; i++){
            power *= 2;
            powers[i] = power;
        }
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int count = n / 2;
            int idx = n - 1;
            long w1 = powers[n], w2 = 0;
            for(int i = 1; i <= count; i++)
                w2 += powers[idx--];
            for(int i = idx; i >= 1; i--)
                w1 += powers[i];
            System.out.println(w1 - w2); //w1 is always greater as it has 2^n
        }
    }
}
//TC: O(N)