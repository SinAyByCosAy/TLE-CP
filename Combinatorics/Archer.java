//https://codeforces.com/problemset/problem/312/B
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class Archer {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        double d = sc.nextDouble();
        double ans = (a * d) / ((a * d) + (c * b) - (a * c));
        System.out.println(ans);
    }
}