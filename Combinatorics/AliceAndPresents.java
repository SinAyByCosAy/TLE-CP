//https://codeforces.com/problemset/problem/1236/B
package DPBootcamp.Combinatorics;

import java.util.Scanner;

//solution = (2^m - 1)^n
public class AliceAndPresents {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int mod = (int) 1e9 + 7;
        int ans = mBinaryExpo((mBinaryExpo(2, m, mod) - 1 + mod) % mod, n, mod);
        System.out.println(ans);
    }
    public static int mBinaryExpo(int a, int b, int mod){
        int res = 1;
        while(b > 0){
            if((b & 1) == 1) res = modMul(res, a, mod);
            a = modMul(a, a, mod);
            b >>= 1;
        }
        return res;
    }
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
}
//TC: O(log m + log n)