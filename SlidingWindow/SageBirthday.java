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
        int i = 0, j = k;
        int lastPicked = -1;
        int idx = 0;
        while(i < k){//logic to reorder the array, going over the valley elments
            if(arr[i] < arr[j]){//since array is sorted, if arr[i] < arr[j] then arr[i] is also < arr[j+1]
                if(lastPicked != j){//we need to pick the left side of the valley
                    result[idx++] = arr[j];
                }
                result[idx++] = arr[i];//valley element
                result[idx++] = arr[j + 1];//right side of the valley
                i++;//next valley element
                lastPicked = j + 1;
            }else{
                if(lastPicked != j){//if this single element hasn't been picked, we need to add it
                    result[idx++] = arr[j];
                    lastPicked = j;
                }
            }
            j++;
        }
        j = lastPicked + 1;
        while(j < n){//add remaining elements
            result[idx++] = arr[j++];
        }
        System.out.println(k);
        for(int ele : result)
            System.out.print(ele+" ");
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
    public static boolean check(int[] arr, int x){
        int n = arr.length;
        if(n - x < x + 1)//base condition for x to be possible
            return false;
        int i = 0, j = x;
        int count = 0;
        while(j < n - 1){
            if(arr[i] < arr[j]){//found left and right elements @ idx: j, j+1
                count ++;
                i++;
                j++;
            }else{//we try larger elements
                j++;
            }
        }
        return count >= x;
    }
}
