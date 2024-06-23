//https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
package DPBootcamp.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MaxSumOfDistinctSubarrayOfSizeK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        System.out.println(maximumSubarraySum(nums, k));
        System.out.println(maxSASumSet(nums, k));
    }

    //solution using array as a map
    public static long maximumSubarraySum(int[] nums, int k){
        int n = nums.length;
        long sum = 0;
        long maxSum = 0;
        int[] map = new int[100001];
        int duplicateCount = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            map[nums[i]]++;
            if(map[nums[i]] > 1)
                duplicateCount ++;

            if(i >= k){
                sum -= nums[i - k];
                map[nums[i - k]]--;
                if(map[nums[i - k]] >= 1)
                    duplicateCount --;
            }

            if(i >= k-1){
                if(duplicateCount == 0)
                    maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }


    //solution using set
    public static long maxSASumSet(int[] nums, int k){
        int n = nums.length;
        int l = 0, r = 0;
        long sum = 0, maxSum = 0;
        HashSet<Integer> hs = new HashSet<>();
        while(r < n){
            if(hs.contains(nums[r])){
                sum -= nums[l];
                hs.remove(nums[l]);
                l++;
            }
            else{
                sum += nums[r];
                hs.add(nums[r]);
                r++;
            }

            if(hs.size() == k){
                maxSum = Math.max(sum, maxSum);
                sum -= nums[l];
                hs.remove(nums[l]);
                l++;
            }
        }
        return maxSum;
    }
}
