//https://codeforces.com/problemset/problem/1569/C
package DPBootcamp.Combinatorics;

import java.util.Scanner;

//solution => N! (if max count > 1) , N! - N!/(c+1) (if count <= 1) c: count of (max - 1)
public class JuryMeeting {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
                max = Math.max(max, arr[i]);
            }
            int mod = 998244353;
            int maxCount = 0;
            int maxMinusOneCount = 0;
            for(int i = 0; i < n; i++) {
                if(arr[i] == max) maxCount ++;
                else if (arr[i] == max - 1) maxMinusOneCount++;
            }

            int Nfact = 1;
            for(int i = 1; i <= n; i++)
                Nfact = modMul(Nfact, i, mod);

            if(maxCount > 1) System.out.println(Nfact);
            else System.out.println((Nfact - modMul(Nfact, mmInvPrime(maxMinusOneCount + 1, mod), mod) + mod) %  mod);
        }
    }
    public static int mmInvPrime(int a, int m){
        int b = m - 2;
        int res = 1;
        while(b > 0){
            if((b & 1) == 1) res = modMul(res, a, m);
            a = modMul(a, a, m);
            b >>= 1;
        }
        return res;
    }
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
}
//TC: O(N)