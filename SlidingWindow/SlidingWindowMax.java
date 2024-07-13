//https://leetcode.com/problems/sliding-window-maximum/
package DPBootcamp.SlidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class SlidingWindowMax {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        System.out.println(Arrays.toString(maxSlidingWindow(arr, k)));
    }
    public static int[] maxSlidingWindow(int[] nums, int k){
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int idx = 0;
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i < n; i++){

            while(!dq.isEmpty() && dq.peekLast() < nums[i]){
                dq.removeLast();
            }
            dq.addLast(nums[i]);

            if(i >= k){
                if(!dq.isEmpty() && dq.peekFirst() == nums[i - k]){
                    dq.removeFirst();
                }
            }
            if(i >= k - 1){
                result[idx++] = dq.peekFirst();
            }
        }
        return result;
    }
}