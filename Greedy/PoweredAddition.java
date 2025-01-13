//https://codeforces.com/contest/1338/problem/A
package DPBootcamp.Greedy;

import java.util.Scanner;

public class PoweredAddition {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

            long maxDiff = 0;
            for(int i = 1; i < n; i++){
                if(arr[i] < arr[i - 1]){
                    int diff = arr[i - 1] - arr[i];
                    maxDiff = Math.max(maxDiff, diff);
                    arr[i] = arr[i - 1];
                }
            }
            if(maxDiff == 0) System.out.println(0);
            else System.out.println((int) (Math.log(maxDiff) / Math.log(2)) + 1); //finding MSB
        }
    }
}
//TC: O(N)