//https://codeforces.com/problemset/problem/1850/F
package DPBootcamp.NumberTheory;

import java.util.Scanner;

public class WeWereBothChildren {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] freq = new int[n + 1];
            for(int i = 0; i < n; i++){
                int x = sc.nextInt();
                if(x <= n)//greater than n, can't be part of the answer
                    freq[x]++; //marking the number of frogs at a particular starting position
            }

            int[] jumps = new int[n + 1];
            int ans = 0;
            for(int i = 1; i <= n; i++){
                if(freq[i] > 0){
                    for(int j = i; j <= n; j += i){
                        jumps[j] += freq[i]; //marking #frogs at their jump positions within the range
                        ans = Math.max(ans, jumps[j]);
                    }
                }
            }
            System.out.println(ans);
        }
    }
}