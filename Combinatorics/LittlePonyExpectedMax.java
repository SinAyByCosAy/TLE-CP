//https://codeforces.com/problemset/problem/453/A
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class LittlePonyExpectedMax {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        double ans = m;
        for(double i = m - 1; i >= 1; i--) //O(m)
            ans -= binaryExpo(i / m, n); //O(log n)

        System.out.println(ans);
    }
    public static double binaryExpo(double a, int b){
        double res = 1.0;
        while(b > 0){
            if((b & 1) == 1) res *= a;
            a *= a;
            b >>= 1;
        }
        return res;
    }
}
//TC: O(m * log n)