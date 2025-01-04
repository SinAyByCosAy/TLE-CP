//https://codeforces.com/contest/1285/problem/B
package DPBootcamp.Greedy;

import java.util.Scanner;

public class JustEatIt {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            long yaseer = 0;
            long adel = 0;
            boolean neg = false;
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
                yaseer += arr[i];
                if(arr[i] >= 0) neg = true;
            }
            if(!neg) System.out.println("NO");
            else{
                long sum = 0;
                for(int i = 0; i < n - 1; i++){
                    sum += arr[i];
                    adel = Math.max(adel, sum);
                    if(sum < 0) sum = 0;
                }
                sum = 0;
                for(int i = 1; i < n; i++){
                    sum += arr[i];
                    adel = Math.max(adel, sum);
                    if(sum < 0) sum = 0;
                }
                if(yaseer > adel) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
}
//TC: O(N)