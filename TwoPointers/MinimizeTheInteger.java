//https://codeforces.com/contest/1251/problem/C
package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class MinimizeTheInteger {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t-- > 0){
            String s = sc.nextLine();
            int n = s.length();
            StringBuilder even = new StringBuilder();
            StringBuilder odd = new StringBuilder();
            StringBuilder ans = new StringBuilder();

            for(int i = 0; i < n; i++){
                char ch = s.charAt(i);
                if((ch - 48) % 2 == 0)
                    even.append(ch);
                else
                    odd.append(ch);
            }
            int p1 = 0, p2 = 0;
            while(p1 < even.length() && p2 < odd.length()){
                char evenDigit = even.charAt(p1);
                char oddDigit = odd.charAt(p2);
                if(evenDigit < oddDigit){
                    ans.append(evenDigit);
                    p1++;
                }else{
                    ans.append(oddDigit);
                    p2++;
                }
            }
            while(p1 < even.length()){
                ans.append(even.charAt(p1));
                p1++;
            }
            while(p2 < odd.length()){
                ans.append(odd.charAt(p2));
                p2++;
            }
            System.out.println(ans);
        }
    }
}