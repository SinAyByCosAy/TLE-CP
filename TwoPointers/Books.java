//https://codeforces.com/contest/279/problem/B
package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class Books {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int i = 0, j = 0;
        int ans = 0;
        int sum = 0;
        while(j < n){
            sum += arr[j];
            while(i <= j && sum > t){
                sum -= arr[i];
                i++;
            }
            ans = Math.max(ans, j - i + 1);
            j++;
        }
        System.out.println(ans);
    }
}