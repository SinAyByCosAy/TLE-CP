//https://codeforces.com/problemset/problem/1102/E
package DPBootcamp.Combinatorics;

import java.util.HashMap;
import java.util.Scanner;

//we'll have K intervals where the first interval will have only 0s and remaining
//(k-1) intervals will have two choices. Hence, answer = 2^(k-1)
public class MonotonicRenumerations {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int mod = 998244353;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < n; i++)
            hm.put(arr[i], i);
        int intervals = 0;
        int lastInterval = -1;
        for(int i = 0; i < n; i++){
            if(i > lastInterval)
                intervals++;
            lastInterval = Math.max(lastInterval, hm.get(arr[i]));
        }
        long ans = 1;
        for(int i = 1; i <= intervals - 1; i++)
            ans = (ans * 2) % mod;

        System.out.println(ans);
    }
}
//TC: O(N)