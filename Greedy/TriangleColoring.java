//https://codeforces.com/contest/1795/problem/D
package DPBootcamp.Greedy;

import java.util.Scanner;

public class TriangleColoring {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        for(int i = 1; i <= n; i++)
            arr[i] = sc.nextInt();
        int mod = 998244353;
        int numerator = 1, denominator = 1;
        for(int i = 1; i <= n/3; i++){// (n/3) C (n/6)
            numerator = modMul(numerator, i, mod);
            if(i == n / 6)
                denominator = modMul(denominator, mmInvPrime(numerator, mod), mod);
        }
        int ans = modMul(numerator, modMul(denominator, denominator, mod), mod);
        for(int i = 1; i <= n - 2; i += 3) { //iterating over triples
            int min;//finding min element in every triple
            if(arr[i] <= arr[i + 1] && arr[i] <= arr[i + 2])
                min = arr[i];
            else if(arr[i + 1] <= arr[i] && arr[i + 1] <= arr[i + 2])
                min = arr[i + 1];
            else
                min = arr[i + 2];
            int count = 0; // finding count of min element in every triple
            if(min == arr[i]) count++;
            if(min == arr[i + 1]) count++;
            if(min == arr[i + 2]) count++;
            ans = modMul(ans, count, mod);
        }
        System.out.println(ans);
    }
    public static int mmInvPrime(int a, int mod){
        int b = mod - 2;
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
//TC: O(N)