//https://codeforces.com/contest/1223/problem/C
package DPBootcamp.BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class SaveTheNature {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while(q-- > 0){
            int n = sc.nextInt();
            long[] p = new long[n];
            for(int i = 0; i < n; i++){
                p[i] = sc.nextLong();
            }
            long x = sc.nextLong();
            long a = sc.nextLong();
            long y = sc.nextLong();
            long b = sc.nextLong();
            long k = sc.nextLong();
            if(x > y){//keeping x value smaller than y for ease of calculation later
                long t = x;
                x = y;
                y = t;
                t = a;
                a = b;
                b = t;
            }
            Arrays.sort(p);
            long start = Math.min(a, b), end = n;
            long ans = -1;
            long lcm = a * b / gcd(a, b);
            while(start <= end){
                long mid = (start + end) / 2;
                if(getContribution(p, mid, x, a, y, b, lcm) >= k){
                    ans = mid;
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            System.out.println(ans);
        }
    }
    public static long getContribution(long[] p, long targetTickets, long x, long a, long y, long b, long lcm){
        long sum = 0;
        long noOfLCM = targetTickets / lcm;
        long noOfB = targetTickets / b - noOfLCM;
        long noOfA = targetTickets / a - noOfLCM;
        long perc = (x + y);
        int idx = p.length - 1;

        while(idx >= 0 && noOfLCM-- > 0){
            sum += (p[idx--] * perc / 100);
        }
        while(idx >= 0 && noOfB-- > 0){
            sum += (p[idx--] * y / 100);
        }
        while(idx >= 0 && noOfA-- > 0){
            sum += (p[idx--] * x / 100);
        }
        return sum;
    }

    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a % b);
    }
}