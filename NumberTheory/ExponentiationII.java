//https://cses.fi/problemset/task/1712
package DPBootcamp.NumberTheory;

import java.io.*;
import java.util.*;

//acc to fermat's little theorem : if m is prime, a^b % m = ( a ^ (b % (m-1)) ) % m
public class ExponentiationII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            b = binaryExpo(b, c, (long)1e9 + 6);
            out.println(binaryExpo(a, b, (long)1e9 + 7));
        }
        out.flush();
    }

    // to find a ^ b, TC : O(log b)
    public static long binaryExpo(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }
}