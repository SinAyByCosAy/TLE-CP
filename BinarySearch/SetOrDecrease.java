//https://codeforces.com/problemset/problem/1622/C
package DPBootcamp.BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class SetOrDecrease {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            long k = sc.nextLong();
            Integer[] arr = new Integer[n];
            long sum = 0;
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
                sum += arr[i];
            }
            Arrays.sort(arr);
            System.out.println(getMinSteps(arr, n, k, sum));
        }
    }
    public static long getMinSteps(Integer[] arr, int n, long k, long sum){//BS on answer
        if(sum <= k)
            return 0;
        long start = 1, end = (long)1e10;
        long ans = end;
        while(start <= end){
            long mid = (start + end) / 2;
            if(check(arr, n, k, sum, mid)){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
    public static boolean check(Integer[] arr, int n, long k, long sum, long noOfOps){
        if(sum - noOfOps <= k)//first sum with only t1 operations and no t2 operations
            return true;
        int t2 = 1;
        for(int i = n-1; i >= Math.max(1, n - noOfOps); i--){//checking all sums with increasing t2 ops and decreasing t1 ops
            sum -= arr[i];
            long checkSum = sum - arr[0] + (t2 + 1) * (arr[0] - (noOfOps - t2));
            if(checkSum <= k)
                return true;
            t2 ++;
        }
        return false;
    }
}