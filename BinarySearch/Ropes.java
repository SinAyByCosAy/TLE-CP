//https://codeforces.com/edu/course/2/lesson/6/2/practice/contest/283932/problem/B
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class Ropes {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        long min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
            min = Math.min(arr[i], min);
        }

        double precision = 1e-7;
        double start = precision, end = 1e7;
        double mid, ans = start;
        for(int i=0;i<50;i++){ //O(log(10^7 * 1/10^7)) ~ 50
            mid = (start + end)/2;
            if(possibleRopes(arr, mid) >= k){
                ans = mid;
                start = mid + precision;
            }else{
                end = mid - precision;
            }
        }
        System.out.println(ans);
    }
    public static int possibleRopes(int arr[], double mid){
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i]/mid;
        }
        return sum;
    }
}