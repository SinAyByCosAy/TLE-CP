//https://codeforces.com/edu/course/2/lesson/6/2/practice/contest/283932/problem/A
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class PackingRectangles {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        long w = sc.nextInt();
        long h = sc.nextInt();
        long n = sc.nextInt();
        System.out.println(getMinSquareLength(w, h, n));
    }
    public static long getMinSquareLength(long w, long h, long n){
        long start = 1, end = (long)Math.pow(10, 18), mid;
        long ans = end;
        while(start <= end){
            mid = (start + end)/2;
            long rows = mid / h;
            long cols = mid / w;
            if(cols > 0 && rows >= (n + cols - 1)/cols){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
}
