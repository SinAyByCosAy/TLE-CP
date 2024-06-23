//https://www.geeksforgeeks.org/first-negative-integer-every-window-size-k/
package DPBootcamp.SlidingWindow;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FirstNegativeInSubarrayOfSizeK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(arr[i] < 0)
                q.add(arr[i]);

            if(i >= k){//no need to remove positive elements as they were never added, remove the negative elements as encountered
                if(!q.isEmpty() && q.peek() == arr[i - k])
                    q.remove();
            }

            if(i >= k - 1){
                if(!q.isEmpty())
                    System.out.print(q.peek()+" ");
                else
                    System.out.print(0+" ");
            }
        }
        System.out.println();
    }
}
