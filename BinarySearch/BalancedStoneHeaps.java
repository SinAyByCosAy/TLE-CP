//https://codeforces.com/problemset/problem/1623/C
package DPBootcamp.BinarySearch;

import java.util.Scanner;
public class BalancedStoneHeaps {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            System.out.println(getSmallestValue(arr));
        }
    }
    public static int getSmallestValue(int[] arr){
        int start = 0, end = (int)1e9;
        int ans = 0;
        while(start <= end){
            int mid = (start + end) / 2;
            if(check(arr, mid)){
                ans = mid;
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return ans;
    }
    public static boolean check(int[] arr, int x){
        int[] total = arr.clone();
        int n = arr.length;
        for(int i = n - 1; i >= 0; i--){
            if(total[i] < x)
                return false;

            int extra = Math.min(total[i] - x, arr[i]); // if extra stones will be abundant(>x) in current heap, then we can't give out more than original #stones = arr[i]
            //if extra stones are limited(<x), then we can only give out (total - x) stones so that current heap can receive stones in future to make it have x stones
            extra /= 3;
            total[i] -= 3 * extra;
            if(i > 1){
                total[i - 1] += extra;
                total[i - 2] += 2 * extra;
            }
        }
        return true;
    }
}