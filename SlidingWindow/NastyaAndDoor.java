//https://codeforces.com/contest/1341/problem/B
package DPBootcamp.SlidingWindow;

import java.util.Scanner;
public class NastyaAndDoor {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int maxPeakCount = 0;
            int l = 0;
            int count = 0;
            boolean[] peakMet = new boolean[n];
            for (int i = 2; i < n; i++) {

                if(arr[i - 2] < arr[i - 1] && arr[i - 1] > arr[i]){// i-1 is a peak element
                    count++; //peak counted
                    peakMet[i - 2] = true; //left side of the peak is marked as it's the starting point of the peak
                }

                if(i >= k && peakMet[i - k]){ //removing the peak going outside of the range
                    count--;
                }

                if(count > maxPeakCount){//checking for answer
                    maxPeakCount = count;
                    l = (i >= k) ? i - k + 1 : 0;
                }
            }
            System.out.println((maxPeakCount + 1) + " " + (l + 1));
        }
    }
}