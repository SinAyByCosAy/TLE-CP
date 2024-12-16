//https://codeforces.com/contest/2044/problem/A
package DPBootcamp.Contests.CF993Div4;

import java.util.Scanner;

public class ProbA {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < n; i++)
//            arr[i] = sc.nextInt();
        for(int i = 1; i <= n; i++){
            int limit = sc.nextInt();
            System.out.println(limit - 1);
        }
    }
}
