//https://codeforces.com/contest/2051/problem/B
package DPBootcamp.Contests.CF995Div3;

import java.util.Scanner;

public class ProbB {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            long sum = a + b + c;
            long rounds = n / sum;
            long count = 3 * rounds;
            long rem = n % sum;
            if(rem == 0) System.out.println(count);
            else if(a >= rem) System.out.println(count + 1);
            else if(a + b >= rem) System.out.println(count + 2);
            else System.out.println(count + 3);
        }
    }
}
