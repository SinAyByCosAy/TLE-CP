//https://codeforces.com/contest/2051/problem/D
package DPBootcamp.Contests.CF995Div3;

import java.util.Arrays;
import java.util.Scanner;

//fix one end BS on the other end
public class ProbD {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            long x = sc.nextLong();
            long y = sc.nextLong();
            Long[] arr = new Long[n];
            long sum = 0;
            for(int i = 0; i < n; i++) {
                arr[i] = sc.nextLong();
                sum += arr[i];
            }
            Arrays.sort(arr);
            if(sum < x) System.out.println(0);
            else{
                long l1 = y <= sum ? sum - y : 0;
                long l2 = sum - x;
                System.out.println(findPairs(arr, l1, l2));
            }
        }
    }
    public static long findPairs(Long[] arr, long l1, long l2){
        int n = arr.length;
        long count = 0;
        for(int i = 0; i < n; i++){
            long ele = arr[i];
            if(ele > l2) break;
            long lb = getLB(arr, Math.abs(l1 - ele), i + 1, n - 1);
            long ub = getUB(arr, Math.abs(l2 - ele), i + 1, n - 1);
            if(lb != -1 && ub != -1) count += ub - lb + 1;
        }
        return count;
    }
    public static long getLB(Long[] arr, long x, int start, int end){
        long ans = -1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] >= x){
                ans = mid;
                end = mid - 1;
            }else start = mid + 1;
        }
        return ans;
    }
    public static long getUB(Long[] arr, long x, int start, int end){
        long ans = -1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] <= x){
                ans = mid;
                start = mid + 1;
            }else end = mid - 1;
        }
        return ans;
    }
}
//TC: O(NlogN)