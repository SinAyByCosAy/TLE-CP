//given N elements, find the maximum AND of two numbers
package DPBootcamp.Greedy;

import java.util.Scanner;

public class MaxAND {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.println(getMaxAND(arr));
    }
    public static int getMaxAND(int[] arr){
        int res = 0;
        for(int i = 31; i >= 0; i--){
            if(countCandidates(arr, (res | (1 << i))) >= 2)
                res |= (1 << i);
        }
        return res;
    }
    public static int countCandidates(int[] arr, int pattern){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if((arr[i] & pattern) == pattern) count++;
        }
        return count;
    }
}
//TC: O(N.32)