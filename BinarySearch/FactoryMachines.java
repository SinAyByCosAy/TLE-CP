//https://cses.fi/problemset/task/1620/
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class FactoryMachines {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        long start = 1, end = (long) 1e18;
        long ans = end;
        while(start <= end){
            long mid = (start + end) / 2;
            if(checkProductsCount(arr, mid, t)){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        System.out.println(ans);
    }
    public static boolean checkProductsCount(int arr[], long time, long target){
        long productCount = 0;
        for(int ele : arr){
            productCount += time / ele;
            if(productCount >= target)
                return true;
        }
        return false;
    }
}
