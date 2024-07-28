//given an array of positive integers, find the length of smallest SA with sum of elements >= k
// [2 3 5 4 1 2 3] k = 9
// => 2   [5 4]
package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class LengthOfSmallestSAWithSumAtleastK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int i = 0, j = 0;
        long sum = 0;
        int ans = n;
        //invariance: left to right is a good segment
        while(j < n){
            sum += arr[j]; //adding the new j term to the sum
            while(i <= j && sum - arr[i] >= k){ //then we try to reduce the distance between right and left by moving the left forward till sum >= k
                sum -= arr[i]; //removing left element
                i++; //moving forward
            }
            if(sum >= k)//we need this condition because initially it'll take time to find a good segment, we don't want answer for bad segments
                ans = Math.min(ans, j - i + 1);
            j++;
        }
        System.out.println(ans);
    }
}
