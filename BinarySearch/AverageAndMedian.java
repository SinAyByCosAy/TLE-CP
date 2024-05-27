//https://atcoder.jp/contests/abc236/tasks/abc236_e
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class AverageAndMedian {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(maxPossibleAverage(arr));
        System.out.println(maxPossibleMedian(arr));
    }
    public static double maxPossibleAverage(int[] arr){
        double start = 0, end = 1e9;
        double precision = 1e4;
        double ans = end;
        int n = arr.length;
        for(int i = 1; i <= 50; i++){
            double mid = (start + end) / 2;
            double[] temp = new double[n];
            for(int idx = 0; idx < n; idx++){
                temp[idx] = arr[idx] - mid;
            }
            if(getMaxSum(temp) >= 0){
                ans = mid;
                start = mid + precision;
            }else{
                end = mid - precision;
            }
        }
        return ans;
    }
    public static int maxPossibleMedian(int[] arr){
        int start = 0, end = (int)1e9;
        int ans = end;
        int n = arr.length;
        while(start <= end){
            int mid = (start + end) / 2;
            double[] temp = new double[n];//since we have to use the same getMaxSum fn., we have to use a double array
            for(int idx = 0; idx < n; idx++){
                if(arr[idx] >= mid) temp[idx] = 1;
                else temp[idx] = -1;
            }
            if(getMaxSum(temp) > 0){
                ans = mid;
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return ans;
    }
    public static double getMaxSum(double[] temp){
        int n = temp.length;
        double[] maxSum = new double[n];
        maxSum[0] = temp[0];
        maxSum[1] = temp[1] + Math.max(maxSum[0], 0);
        for(int i = 2; i < n; i++){
            maxSum[i] = temp[i] + Math.max(maxSum[i-1], maxSum[i-2]);
        }
        return Math.max(maxSum[n-1], maxSum[n-2]);
    }
}