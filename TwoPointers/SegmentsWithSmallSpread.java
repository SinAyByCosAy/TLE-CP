//https://codeforces.com/edu/course/2/lesson/9/2/practice/contest/307093/problem/F
package DPBootcamp.TwoPointers;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
public class SegmentsWithSmallSpread {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] arr = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextLong();
        }
        Deque<Long> min = new LinkedList<>();
        Deque<Long> max = new LinkedList<>();
        int i = 0, j = 0;
        long ans = 0;
        while(j < n){
            //every element gets added in both the deques
            while(!min.isEmpty() && min.peekLast() > arr[j])//larger element present before can't be an answer for min
                min.removeLast();
            min.addLast(arr[j]);
            while(!max.isEmpty() && max.peekLast() < arr[j])//smaller element present before can't be an answer for max
                max.removeLast();
            max.addLast(arr[j]);

            while(i <= j && max.peekFirst() - min.peekFirst() > k){
                //we need to remove the anomaly min or max element
                if(arr[i] == max.peekFirst())
                    max.removeFirst();
                else if(arr[i] == min.peekFirst())
                    min.removeFirst();
                i++;
            }
            ans += (j - i + 1);
            j++;
        }
        System.out.println(ans);
    }
}