//https://atcoder.jp/contests/abc169/tasks/abc169_d
package DPBootcamp.NumberTheory;

import java.util.Scanner;

public class DivGame {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long ans = 0;
        for(long i = 2; i * i <= n; i++){
            if(n % i == 0){//found a prime factor
                long target = 1;//targets: 1 + 2 + 3 .... till all powers are covered
                long count = 0;
                while(n % i == 0){//Some constant C ops
                    n /= i;
                    count++;
                    if(count == target){
                        count = 0;//resetting count to check if we can meet the next target
                        target++;//next target
                        ans++;//counting new factor
                    }
                }
            }
        }
        if(n > 1) ans++;//we have one more prime
        System.out.println(ans);
    }
}
//TC: O(sqrt(N) * C)