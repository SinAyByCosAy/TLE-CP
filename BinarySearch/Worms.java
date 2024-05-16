//https://codeforces.com/contest/474/problem/B
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class Worms {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n+1];
        for(int i=1;i<=n;i++){
            arr[i] = arr[i-1] + sc.nextInt(); //updating array to have ranges during input itself
        }
        int m = sc.nextInt();
        int queries[] = new int[m];
        for(int i=0;i<m;i++){
            queries[i] = sc.nextInt();
        }
        for(int i=0;i<m;i++){
            System.out.println(getPileNumber(arr, n, queries[i]));
        }
    }
    public static int getPileNumber(int arr[], int n, int q){
        int start = 1, end = n;
        int ans = end;
        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] < q){
                start = mid + 1;
            }else{
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }
}
