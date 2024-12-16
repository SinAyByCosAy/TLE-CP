//https://codeforces.com/contest/2044/problem/B
package DPBootcamp.Contests.CF993Div4;

import java.util.Scanner;

public class ProbB {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++){
            String s = sc.nextLine();
            int p1 = 0, p2 = s.length() - 1;
            StringBuffer res = new StringBuffer();
            for(int j = p2; j >= p1; j--){
                char ch = s.charAt(j);
                if(ch == 'p') ch = 'q';
                else if(ch == 'q') ch = 'p';
                res.append(ch);
            }
            System.out.println(res);
        }
    }
}
