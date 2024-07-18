package DPBootcamp.SlidingWindow;

import java.util.Arrays;
import java.util.Scanner;

public class KRadiusSubarrayAvgs {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        System.out.println(Arrays.toString(getAverages(arr, k)));
    }
    public static int[] getAverages(int[] nums, int k){
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        long sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];

            if(i >= 2 * k + 1)
                sum -= nums[i - (2 * k + 1)];

            if(i >= 2 * k)
                result[i - k] = (int)(sum / (2 * k + 1));
        }
        return result;
    }
}
