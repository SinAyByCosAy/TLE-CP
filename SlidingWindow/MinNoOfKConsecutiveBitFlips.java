package DPBootcamp.SlidingWindow;

import java.util.Scanner;

public class MinNoOfKConsecutiveBitFlips {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        System.out.println(minKBitFlips(arr, k));
    }
    public static int minKBitFlips(int[] nums, int k){
        int n = nums.length;
        boolean[] flipped = new boolean[n];
        int count = 0;//carry forward flip count
        int maxFlip = 0;//answer
        for(int i = 0; i < n; i++){
            if(i >= k && flipped[i - k]){//remove the out of range bit flip
                count --;
            }
            if(count % 2 == nums[i]){//bit requires flipping
                if(i > n - k)//these bit should not require flipping
                    return -1;
                count ++;
                maxFlip ++;
                flipped[i] = true;//marking bit flipped
            }
        }
        return maxFlip;
    }
}
