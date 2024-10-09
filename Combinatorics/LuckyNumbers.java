//https://codeforces.com/problemset/problem/630/C
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class LuckyNumbers {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println((1l << (n + 1)) - 2);
    }
}
//O(1)