//https://codeforces.com/problemset/problem/1419/D2
package DPBootcamp.SlidingWindow;

import java.util.Arrays;
import java.util.Scanner;

public class SageBirthday {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int k = getPossibleGroups(arr);
        int[] result = new int[n];
        int i = 0, j = n - (k + 1);//first index of the (k+1) largest elements
        int idx = 0;
        while (i < k) {//logic to reorder the array, going over the valley elements
            if (i == 0) {
                result[idx++] = arr[j];
            }
            result[idx++] = arr[i];
            result[idx++] = arr[j + 1];
            i++;
            j++;
        }
        i = k;
        j = (k == 0) ? n-1 : n - (k + 2);//last index of leftover elements
        while (i <= j) {//add remaining elements
            result[idx++] = arr[i++];
        }
        System.out.println(k);
        for (int ele : result)
            System.out.print(ele + " ");
    }
    public static int getPossibleGroups(int[] arr){//BS on answer
        int n = arr.length;
        int start = 0, end = (n+1) / 2;
        int ans = 0;
        while(start <= end){
            int mid = (start + end) / 2;
            if(check(arr, mid)){
                start = mid + 1;
                ans = mid;
            }else{
                end = mid - 1;
            }
        }
        return ans;
    }
    public static boolean check(int[] arr, int k){
        int n = arr.length;
        if(n - k < k + 1)//base condition for x to be possible
            return false;
        int i = 0, j = n - (k + 1);
        while(i < k){
            if(arr[i] >= arr[j]){//not possible
                break;
            }
            i++;
            j++;
        }
        return i == k;
    }
}