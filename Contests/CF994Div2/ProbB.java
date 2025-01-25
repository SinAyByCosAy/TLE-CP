//https://codeforces.com/contest/2049/problem/B
package DPBootcamp.Contests.CF994Div2;

import java.util.Scanner;

public class ProbB {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            sc.nextLine();
            String str = sc.nextLine();
            int len = str.length();
            int pCount = 0, sCount = 0;
            for(int i = 0; i < len; i++){
                char ch = str.charAt(i);
                if(ch == 's') sCount++;
                else if(ch == 'p') pCount++;
            }
            if(sCount == 0 || pCount == 0) System.out.println("YES");
            else if((sCount == 1 && str.charAt(0) == 's') || (pCount == 1 && str.charAt(len - 1) == 'p'))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
