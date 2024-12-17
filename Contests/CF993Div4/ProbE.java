//https://codeforces.com/contest/2044/problem/E
package DPBootcamp.Contests.CF993Div4;

import java.util.Scanner;

public class ProbE {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int k = sc.nextInt();
            int l1 = sc.nextInt();
            int r1 = sc.nextInt();
            int l2 = sc.nextInt();
            int r2 = sc.nextInt();
            long count = 0;
            for(long p = 1; p <= r2; p *= k){
                long l = Math.max(l1, (l2 + p - 1) / p);
                long r = Math.min(r1, r2 / p);
                if(l <= r) count += (r - l + 1);
            }
            System.out.println(count);
        }
    }
}
