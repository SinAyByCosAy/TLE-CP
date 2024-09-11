//https://codeforces.com/contest/1826/problem/B
package DPBootcamp.NumberTheory;

import java.util.Scanner;

//looking for highest common factor b/w all | A[i] - A[n - (i+1)] | values
public class LunaticNeverContent {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            int ans = 0;
            int idx = 0;
            while(idx <= n/2){
                ans = gcd(ans, Math.abs(arr[idx] - arr[n - (idx + 1)]));
                idx++;
            }
            System.out.println(ans);
        }
    }
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
