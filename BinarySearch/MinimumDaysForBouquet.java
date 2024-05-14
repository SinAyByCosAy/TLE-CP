package DPBootcamp.BinarySearch;

import java.awt.event.MouseAdapter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class MinimumDaysForBouquet {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
            max = Math.max(max, arr[i]);
        }
        int m = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(minDays(arr, m, k, max));
    }
    public static int minDays(int[] bloomDay, int m, int k, int max){
        if(1l*m*k > bloomDay.length) return -1;
        int start = 1, end = max;
        int ans = end;
        while(start <= end){
            int mid = (start + end) / 2;
            if(check(bloomDay, m, k, mid)){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
    public static boolean check(int[] bloomDay, int m, int k, int target){
        int n = bloomDay.length;
        int eleCount = 0;//elements in current window
        int bouquetCount = 0;//buquets made
        for(int i=0;i<n;i++){
            if(bloomDay[i] > target){
                eleCount = 0;
            }else{
                eleCount ++;
                if(eleCount == k){
                    bouquetCount ++;
                    eleCount = 0;
                }
            }
        }
        if(bouquetCount >= m)
            return true;
        return false;
    }
}
