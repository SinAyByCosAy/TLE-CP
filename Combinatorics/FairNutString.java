//https://codeforces.com/problemset/problem/1084/C
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class FairNutString {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int countA = 0;
        int ans = 0;
        int mod = (int)1e9 + 7;
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == 'a') //counting A in current group
                countA++;
            if(ch == 'b' || i == n - 1) { //group of A ends here, time to calculate
                ans = ((ans + countA) % mod + modMul(countA, ans, mod)) % mod;
                countA = 0; //reset count for next group
            }
        }
        System.out.println(ans);
    }
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
}
//TC: O(N)