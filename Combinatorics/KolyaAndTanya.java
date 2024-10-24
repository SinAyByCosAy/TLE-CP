//https://codeforces.com/problemset/problem/584/B
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class KolyaAndTanya {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int mod = (int)1e9 + 7;

        //answer = allCases - badCases => 3^(3n) - 7^n
        long allCases = 1;
        for(int i = 1; i <= 3 * n; i++)
            allCases = (allCases * 3) % mod;

        long badCases = 1;
        for(int i = 1; i <= n; i++)
            badCases = (badCases * 7) % mod;

        long ans = (allCases - badCases + mod) % mod;
        System.out.println(ans);
    }
}
//TC: O(N)