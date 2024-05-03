package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class FairWorkLoad {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        System.out.println(getMaxWorkload(arr, n, k));
    }
    public static int getMaxWorkload(int arr[], int n, int k){
        int max = Integer.MIN_VALUE;
        int sumOfAllElements = 0;
        for(int ele : arr){
            sumOfAllElements += ele;
            max = Math.max(max, ele);
        }
        int s = max, e = sumOfAllElements, m;
        int ans = Integer.MAX_VALUE;

        while(s <= e){
            m = (s+e)/2;
            if(checkCandidateWorkload(arr, n, k, m)){
                ans = m;
                e = m - 1;
            }else{
                s = m + 1;
            }
        }
        return ans;
    }

    public static boolean checkCandidateWorkload(int arr[], int n, int k, int limit){
        int workers = 1;
        int sum = 0;
        for(int i=0;i<n;i++){
            if(sum + arr[i] > limit){
                workers++;
                sum = arr[i];
            }else{
                sum += arr[i];
            }
            if(workers > k)
                return false;
        }
        return true;
    }
}
