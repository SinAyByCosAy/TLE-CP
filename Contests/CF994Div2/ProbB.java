package DPBootcamp.Contests.CF994Div2;

import java.util.Arrays;
import java.util.Scanner;

public class ProbB {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            sc.nextLine();
            String str = sc.nextLine();
            int[] p = new int[n];
            int[] s = new int[n];
            Arrays.fill(p, Integer.MAX_VALUE);
            Arrays.fill(s, Integer.MAX_VALUE);
            for(int i = 0; i < n; i++){
                char ch = str.charAt(i);
                if(ch == 'p'){
                    p[i] = i + 1;
                }
                if(ch == 's')
                    s[i] = n - i;
            }
            for(int i = 1; i < n; i++)
                s[i] = Math.min(s[i], s[i - 1]);
            for(int i = n - 2; i >= 0; i--)
                p[i] = Math.min(p[i], p[i + 1]);
            int[] ops = new int[n];
            for(int i = 0; i < n; i++)
                ops[i] = Math.min(p[i], s[i]);
            Arrays.sort(ops);
            boolean flag = true;
            for(int i = 0; i < n; i++){
                if(ops[i] < i + 1){
                    flag = false;
                    break;
                }
            }
            if(flag) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
