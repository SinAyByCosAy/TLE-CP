//https://codeforces.com/problemset/problem/1225/B2
package DPBootcamp.TwoPointers;

import java.util.HashMap;
import java.util.Scanner;

public class TVSubscriptions {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int d = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            int ans = k; //max ans can be all shows
            HashMap<Integer, Integer> hm = new HashMap<>();
            for(int i = 0; i < n; i++){
                hm.put(arr[i], hm.getOrDefault(arr[i], 0) + 1);

                if(i >= d){
                    int rem = arr[i - d];
                    hm.put(rem, hm.get(rem) - 1);
                    if(hm.get(rem) == 0)
                        hm.remove(rem);
                }
                if(i >= d - 1)
                    ans = Math.min(ans, hm.size());
            }
            System.out.println(ans);
        }
    }
}