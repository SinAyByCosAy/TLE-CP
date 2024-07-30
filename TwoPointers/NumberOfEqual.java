//https://codeforces.com/edu/course/2/lesson/9/1/practice/contest/307092/problem/C
package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class NumberOfEqual {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        for(int i = 0; i < m; i++){
            b[i] = sc.nextInt();
        }
        int p1 = 0, p2 = 0;
        long ans = 0;
        while(p1 < n && p2 < m){
            if(a[p1] == b[p2]){
                //if elements are equal, count duplicates
                long count1 = 1;
                while(p1 + 1 < n && a[p1] == a[p1 + 1]){//counting duplicates
                    count1++;
                    p1++;
                }
                long count2 = 1;
                while(p2 + 1 < m && b[p2] == b[p2 + 1]){
                    count2++;
                    p2++;
                }
                ans += count1 * count2;
                p1++; //move forward in array1
                p2++; //move forward in array2
            }
            else if(a[p1] < b[p2]) p1++; //move forward in array1
            else p2++; //move forward in array2
        }
        System.out.println(ans);
    }
}