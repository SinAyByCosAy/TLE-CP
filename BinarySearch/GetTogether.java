//https://codeforces.com/edu/course/2/lesson/6/3/practice/contest/285083/problem/A
package DPBootcamp.BinarySearch;


import java.util.Scanner;
public class GetTogether {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long x[] = new long[n];
        long v[] = new long[n];
        for(int i=0;i<n;i++){
            x[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        double start = 0, end = 1e9;
        double ans = 1e9;
        double precision = 1e-7;
        for(int i=0;i<=100;i++){
            double mid = (start + end) / 2;
            if(check(x, v, n, mid)){
                ans = mid;
                end = mid - precision;
            }else{
                start = mid + precision;
            }
        }
        System.out.println(ans);
    }
    public static boolean check(long x[], long v[], int n, double t){
        double rangeLeft = -1e18, rangeRight = 1e18;//arbitrary value for the intersection of the very first range
        for(int i=0; i<n; i++){
            double currLeft = x[i] - t * v[i];
            double currRight = x[i] + t * v[i];
            rangeLeft = Math.max(rangeLeft, currLeft);
            rangeRight = Math.min(rangeRight, currRight);
            if(rangeLeft > rangeRight)
                return false;
        }
        return true;
    }
}
