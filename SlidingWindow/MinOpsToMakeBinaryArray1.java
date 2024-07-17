package DPBootcamp.SlidingWindow;

import java.util.Scanner;

public class MinOpsToMakeBinaryArray1 {
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
        int n = nums.length;
        int count = 0;
        for(int i = 0; i < n ; i++){
            if(i > n - 3 && nums[i] == 0)
                return -1;

            if(nums[i] == 0){
                nums[i+1] ^= 1;
                nums[i+2] ^= 1;
                count++;
            }
        }
        return count;
    }
}
