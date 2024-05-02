//Given a range L to R, find number of elements that fall in that range in a sorted array
/*
Ex - 1 2 3 5 6 7 8 10 11 16 20
    L:4 and R:12
    ans = 6 elements  [5, 6, 7, 8, 10, 11]
 */

//Logic: A = No of elements <= R
//       B = No of elements < L
//       Ans = A - B
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class BSNumbersInRange {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int arr[] = {1, 2, 3, 5, 6, 7, 8, 10, 11, 16, 20};
        System.out.println("Enter L");
        int l = sc.nextInt();
        System.out.println("Enter R");
        int r = sc.nextInt();
        int ansL = noOfEle(arr, l-1);
        int ansR = noOfEle(arr, r);
        System.out.println(ansR-ansL);
    }
    public static int noOfEle(int arr[], int x){
        int s = 0, e = arr.length-1, m;
        int ans = 0;
        while(s<=e){
            m = (int)(s + e)/2;
            if(arr[m] <= x){
                ans = m + 1;
                s = m + 1;
            }else{
                e = m - 1;
            }
        }
        return ans;
    }
}
