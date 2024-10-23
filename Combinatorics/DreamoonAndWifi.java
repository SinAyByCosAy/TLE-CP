//https://codeforces.com/problemset/problem/476/B
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class DreamoonAndWifi {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int n = s1.length();
        int reqDist = 0;
        for(int i = 0; i < n; i++){
            if(s1.charAt(i) == '+') reqDist += 1;
            else reqDist -= 1;
        }
        int y = 0; //# '?'s counter
        int interpretedDist = 0;
        for(int i = 0; i < n; i++){
            char ch = s2.charAt(i);
            if(ch == '+') interpretedDist += 1;
            else if(ch == '-') interpretedDist -= 1;
            else y++; //found '?'
        }
        double ans = 0.0;
        if(y == 0){// no '?'s, hence only one path
            if(interpretedDist == reqDist) ans = 1.0;
            else ans = 0.0;
        }else{
            int d = Math.abs(reqDist - interpretedDist); //remaining distance
            int negOnes = (y - d) / 2; // negative ones req
            int posOnes = negOnes + d; // positive ones req
            if(d > y || negOnes + posOnes != y) ans = 0.0; //no command combinations lead to correct position
            else ans = nCr(y, negOnes) / Math.pow(2, y); //we have possible answers
        }
        System.out.println(ans);
    }
    public static int nCr(int n, int r){
        long ans = 1;
        for(int i = 0; i < r; i++){
            ans *= (n - i);
            ans /= (i + 1);
        }
        return (int) ans;
    }
}
//TC: O(N)