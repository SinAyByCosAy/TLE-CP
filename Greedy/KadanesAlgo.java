package DPBootcamp.Greedy;

import java.util.Scanner;

public class KadanesAlgo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int sum = 0, maxS = 0;
        for(int ele : arr){
            sum += ele;
            maxS = Math.max(maxS, sum);
            if(sum < 0) sum = 0;
        }
        System.out.println(maxS);
    }
}
//TC: O(N)