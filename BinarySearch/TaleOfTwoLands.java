//https://codeforces.com/problemset/problem/1166/C
package DPBootcamp.BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class TaleOfTwoLands {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] points = new int[n];
        for (int i = 0; i < n; i++) {
            points[i] = Math.abs(sc.nextInt());
        }
        Arrays.sort(points);
        long ans = 0;
        for(int i=0;i<n;i++){
            int pos = upperBound(points, 2 * points[i]); // pairs for a particular x: a[i], lie in the range [a[i], 2*a[i]]
            ans += pos - i - 1; //getting the total number of elements from a[i] to 2*a[i], all will be pairs
        }
        System.out.println(ans);
    }
    public static int upperBound(int[] points, int target){
        int start = 0, end = points.length - 1;
        int ans = points.length;
        while(start <= end){
            int mid = (start + end) / 2;
            if(points[mid] > target){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
}
//TC: O(N * log(N))