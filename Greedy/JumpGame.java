//https://leetcode.com/problems/jump-game/
package DPBootcamp.Greedy;

import java.util.Scanner;

public class JumpGame {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.println(canJump(arr));
    }
    public static boolean canJump(int[] nums){
        int maxIndex = 0; //max limit till where our index can go
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(i > maxIndex) return false; //we breached the limit
            if(nums[i] + i > maxIndex) maxIndex = nums[i] + i; //if we can make a farther jump than before, we note it
        }
        return true;
    }
}
