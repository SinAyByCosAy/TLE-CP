//Given 2 sorted arrays, for each element in the first array find # elements smaller than that in the second array
//ex - A [1, 4, 5, 9]  B [2, 3, 6, 10]
//   => 7

package DPBootcamp.TwoPointers;

import java.util.Scanner;

public class FindNoOfSmallerEleIn2ndArray {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] b = new int[m];
        for(int i = 0; i < m; i++){
            b[i] = sc.nextInt();
        }
        int ans = 0;
        int i = 0, j = 0;
        while(i < n){
            while(j < m && a[i] > b[j])
                j++;
            ans += j;
            i++;
        }
        System.out.println(ans);
    }
}
