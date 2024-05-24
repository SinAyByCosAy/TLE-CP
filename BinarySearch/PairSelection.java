//https://codeforces.com/edu/course/2/lesson/6/4/practice/contest/285069/problem/C
package DPBootcamp.BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class PairSelection {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i=0;i<n;i++){
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        System.out.println(getHighestRatio(a, b, k));
    }
    public static double getHighestRatio(int[] a, int[] b, int k){
        double start = 0, end = 1e5;
        double precision = 1e-7;
        double ans = end;
        for(int i=1;i<=50;i++){
            double mid = (start + end) / 2;
            if(checkRatio(a, b, k, mid)){
                ans = mid;
                start = mid + precision;
            }else{
                end = mid - precision;
            }
        }
        return ans;
    }
    public static boolean checkRatio(int[] a, int[] b, int k, double testRatio){
        int n = a.length;
        double[] c = new double[n];
        for(int i=0;i<n;i++){//preparing new array
            c[i] = a[i] - testRatio * b[i];
        }
        Arrays.sort(c);
        double sum = 0;
        for(int i=n-1;i>=n-k;i--){//picking highest k elements
            sum += c[i];
        }
        return sum >= 0;
    }
}
