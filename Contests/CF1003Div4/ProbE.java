//https://codeforces.com/contest/2065/problem/E
package DPBootcamp.Contests.CF1003Div4;

import java.util.Scanner;

public class ProbE {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int max = Math.max(n, m), min = Math.min(n, m);
            if(!(k >= max - min && k <= max)) System.out.println(-1);
            else{
                int curr0 = 0, curr1 = 0;
                int count0 = 0, count1 = 0;
                int bit = (n > m) ? 0 : 1;
                for(int i = 1; i <= n + m; i++){
                    if(bit == 0){
                        System.out.print(0);
                        curr0++;
                        count0++;
                        if(curr0 == k || count0 == n){
                            bit ^= 1;
                            curr0 = 0;
                        }
                    }else{
                        System.out.print(1);
                        curr1++;
                        count1++;
                        if(curr1 == k || count1 == m){
                            bit ^= 1;
                            curr1 = 0;
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}
//TC: O(N + M)