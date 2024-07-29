//Type: Find number of good segments

//given an array, find number of subarrays with sum <= k
// [3 6 2 7 1 5]  k = 6
// [3] [6] [2] [1] [1 5] => 6

package DPBootcamp.TwoPointers;

import java.util.Scanner;
public class NoOfSAwithSumAtmostK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int i = 0, j = 0;
        int count = 0;
        long sum = 0;
        while(j < n){
            sum += arr[j]; //adding the jth term
            while(i <= j && sum > k){//finding a good segment
                sum -= arr[i];
                i++;
            }
            count += (j - i + 1); //adding all SAs for a good segment ending at every j
            j++;
        }
        System.out.println(count);
    }
}
