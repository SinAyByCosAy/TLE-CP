//https://leetcode.com/problems/nth-magical-number/submissions/1258990298/
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class NthMagicalNumber {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(nthMagicalNumber(n, a, b));
    }
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
    public static int nthMagicalNumber(int n, int a, int b){
        int lcm = a * b / gcd(a, b);
        int mod = 1000 * 1000 * 1000 + 7;
        long start = 1, end = (long)1e14;
        long ans = end;
        while(start <= end){
            long mid = (start + end) / 2;
            long pos = mid/a + mid/b - mid/lcm;
            if(pos < n){
                start = mid + 1;
            }else{
                ans = mid;
                end = mid - 1;
            }
        }
        return (int)(ans%mod);
    }
}
