//https://cses.fi/problemset/task/1085
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class ArrayDivision {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        int max = Integer.MIN_VALUE;
        long sum = 0;
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
            max = Math.max(max, arr[i]);
            sum += arr[i];
        }
        long start = max, end = sum;
        long ans = end;
        while(start <= end){
            long mid = (start + end)/2;
            if(getSA(arr, n, mid) <= k){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        System.out.println(ans);
    }

    public static int getSA(int arr[], int n, long target){
        int count = 1;
        long sum = 0;
        for(int i=0; i<n; i++){
            if(sum + arr[i] > target){
                count ++;
                sum = 0;
            }
            sum += arr[i];
        }
        return count;
    }
}
//TC: O(log(sum of all elements) * N)
