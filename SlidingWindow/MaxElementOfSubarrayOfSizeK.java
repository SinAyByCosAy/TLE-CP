//https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
package DPBootcamp.SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MaxElementOfSubarrayOfSizeK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i < n; i++){
            while(!dq.isEmpty() && dq.peekLast() < arr[i]){
                dq.removeLast();
            }
            dq.addLast(arr[i]);

            if(i >= k){
                if(dq.peekFirst() == arr[i - k])
                    dq.removeFirst();
            }

            if(i >= k-1){
                System.out.print(dq.peekFirst() + " ");
            }
        }
        System.out.println();
    }
}
