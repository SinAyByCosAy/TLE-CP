//https://codeforces.com/edu/course/2/lesson/9/3/practice/contest/307094/problem/C
package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class CheCity {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int i = 0, j = 1;
        long ans = 0;
        while(j < n){
            while(i <= j && arr[j] - arr[i] > r){//shrinking till invalid segment
                i++;
            }
            //don't need a condition here to make sure segment is formed because i won't move forward initially, hence i = 0 so ans = 0
            ans += i;
            j++;
        }
        System.out.println(ans);
    }
}