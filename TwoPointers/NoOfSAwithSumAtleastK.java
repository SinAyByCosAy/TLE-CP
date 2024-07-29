package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class NoOfSAwithSumAtleastK {
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
        //if [i,j] is the closest valid SA with sum >= k
        //then all SAs in this segment ending at j other than the full segment are invalid SAs
        //#invalid SAs in [i,j] = (j - i)
        //therefore, #valid SAs = [ All SAs in [0,j] - All invalid SAs in [i,j] ]   ==>  [(j-0+1) - (j-i)] = (i + 1) for every j
        while(j < n){
            sum += arr[j];
            while(i <= j && sum - arr[i] >= k){
                sum -= arr[i];
                i++;
            }
            if(sum >= k)
                count += (i + 1);
            j++;
        }
        System.out.println(count);
    }
}
