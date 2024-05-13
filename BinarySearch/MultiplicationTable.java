//https://cses.fi/problemset/task/2422/
//package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long start = 1, end = n * n;
        long ans = end;
        while(start <= end){
            long mid = (start + end) / 2;
            if(check(mid, n)){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        System.out.println(ans);
    }
    public static boolean check(long x, long n){
        long count = 0;
        for(int row=1; row<=n; row++){
            count += Math.min(n, x/row);
        }
        if(count < ((n*n)/2 + 1))
            return false;
        return true;
    }
}
