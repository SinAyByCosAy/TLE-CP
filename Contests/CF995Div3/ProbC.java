//https://codeforces.com/contest/2051/problem/C
package DPBootcamp.Contests.CF995Div3;

import java.util.HashSet;
import java.util.Scanner;

public class ProbC {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int[] q = new int[m];
            HashSet<Integer> hs = new HashSet<>();
            for(int i = 0; i < m; i++)
                q[i] = sc.nextInt();
            for(int i = 0; i < k; i++)
                hs.add(sc.nextInt());
            if (k < n - 1) {
                for (int i = 1; i <= m; i++) System.out.print(0);
            } else if (k == n)
                for (int i = 1; i <= m; i++) System.out.print(1);
            else {
                for(int i = 0; i < m; i++){
                    if(!hs.contains(q[i])) System.out.print(1);
                    else System.out.print(0);
                }
            }
            System.out.println();
        }
    }
}
