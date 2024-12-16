//https://codeforces.com/contest/2044/problem/B
package DPBootcamp.Contests.CF993Div4;

import java.util.Scanner;

public class ProbB {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int seats = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int res = 0;
            int row1 = seats;
            int row2 = seats;
            res += Math.min(a, row1);
            res += Math.min(b, row2);
            int rem = row1 + row2 - res;
            res += Math.min(rem, c);
            System.out.println(res);
        }
    }
}
