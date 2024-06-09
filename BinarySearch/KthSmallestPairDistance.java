//https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/
package DPBootcamp.BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class KthSmallestPairDistance {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        System.out.println(smallestDistancePair(arr, k));
    }
    static public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int start = 0, end = nums[n-1] - nums[0];
        int ans = -1;
        while(start <= end){//BS on answer
            int mid = (start + end) / 2;
            int count = 0;
            int j=1;
            for(int i=0;i<n;i++){//checking for pairs in linear time
                while(j<n && nums[j] - nums[i] <= mid) {
                    j++;
                }
                count += (j-1-i);
            }
            if(count >= k){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
}
