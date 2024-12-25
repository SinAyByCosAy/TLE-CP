//https://leetcode.com/problems/maximum-score-of-spliced-array/
package DPBootcamp.Greedy;

import java.util.Scanner;

public class MaxScoreSplicedArray {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums1 = new int[n];
        int[] nums2 = new int[n];
        for(int i = 0; i < n; i++)
            nums1[i] = sc.nextInt();
        for(int i = 0; i < n; i++)
            nums2[i] = sc.nextInt();
        System.out.println(maximumsSplicedArray(nums1, nums2));
    }
    public static int maximumsSplicedArray(int[] nums1, int[] nums2){
        return Math.max(getMaxSum(nums1, nums2), getMaxSum(nums2, nums1));
    }
    public static int getMaxSum(int[] nums1, int[] nums2){
        int n = nums1.length;
        int sum = 0;
        for(int ele : nums1) sum += ele;
        int[] d = new int[n];
        for(int i = 0; i < n; i++) d[i] = nums2[i] - nums1[i];
        int maxSum = 0;
        int currSum = 0;
        for(int i = 0; i < n; i++){
            currSum += d[i];
            maxSum = Math.max(maxSum, currSum);
            if(currSum < 0) currSum = 0;
        }
        return sum + maxSum;
    }
}
//TC:O(N)