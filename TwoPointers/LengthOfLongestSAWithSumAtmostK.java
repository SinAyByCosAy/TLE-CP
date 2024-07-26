//Given an array, find the length of the longest SA with sum <= K
//ex - [10 2 3 4 1 1 2 1 5] k = 9
//   => len = 5   [4 1 1 2 1]
package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class LengthOfLongestSAWithSumAtmostK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int k = sc.nextInt();
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int ans = 0;
        int i = 0, j = 0;
        long sum = 0;
        while(j < n){//looking from right end's POV
            sum += arr[j]; //adding new term to SA
            while(i <= j && sum > k){ //if sum exceeds the limit, we decrease SA by removing left most element and shifting left forward
                sum -= arr[i];
                i++;
            }
            ans = Math.max(ans, j - i + 1);//we have a valid SA now with sum <= k
            j++;//we move to the next element on right end
        }
        System.out.println(ans);
    }
}
