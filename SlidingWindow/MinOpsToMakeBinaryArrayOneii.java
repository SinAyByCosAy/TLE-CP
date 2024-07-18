//https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii/
package DPBootcamp.SlidingWindow;

import java.util.Scanner;

public class MinOpsToMakeBinaryArrayOneii {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(minOperations(arr));
    }
    public static int minOperations(int[] nums) {
        int count = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(nums[i] == count % 2)
                count++;
        }
        return count;
    }
}
