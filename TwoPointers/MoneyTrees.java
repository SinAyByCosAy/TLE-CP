//https://codeforces.com/problemset/problem/1873/F
package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class MoneyTrees {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] fruits = new int[n];
            int[] height = new int[n];
            for(int i = 0; i < n; i++){
                fruits[i] = sc.nextInt();
            }
            for(int i = 0; i < n; i++){
                height[i] = sc.nextInt();
            }
            int i = 0, j = 0;
            int ans = 0;
            long sum = 0;
            while(j < n){
                sum += fruits[j]; //include element
                while(i <= j && sum > k){
                    //adjust segment
                    sum -= fruits[i];
                    i++;
                }
                ans = Math.max(ans, j - i + 1);
                j++;
                if(j != n && height[j - 1] % height[j] != 0){//if condition checks, start a new segment from current element
                    i = j;
                    sum = 0;
                }
            }
            System.out.println(ans);
        }
    }
}