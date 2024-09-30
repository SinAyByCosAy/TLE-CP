//https://cses.fi/alon/task/1715
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class CreatingStringsII {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int mod = (int) 1e9 + 7;
        int[] freq = new int[26];
        for(char ch : s.toCharArray()){//frequency of each character
            freq[ch - 'a']++;
        }
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for(int i = 1; i <= n; i++)//1! to N! values, O(N)
            factorial[i] = modMul(factorial[i - 1], i, mod);

        int ans = factorial[n];
        for(int i = 0; i < 26; i++){//O(26.log m)
            if(freq[i] > 1){//need nN!/ai!
                ans = modMul(ans, mmInverse(factorial[freq[i]], mod), mod);
            }
        }
        System.out.println(ans);
    }
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
    public static int mmInverse(int a, int m){//O(log m)
        int b = m - 2;
        int res = 1;
        while(b > 0){
            if((b & 1) == 1){
                res = modMul(res, a, m);
            }
            a = modMul(a, a, m);
            b >>= 1;
        }
        return res;
    }
}
//TC: O(N + 26.log m)