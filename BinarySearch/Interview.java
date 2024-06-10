//https://codeforces.com/problemset/problem/1807/E
package DPBootcamp.BinarySearch;

import java.util.Scanner;
public class Interview {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] weights = new int[n+1];
            int[] ps = new int[n+1];
            for(int i=1;i<=n;i++){
                weights[i] = sc.nextInt();
                ps[i] = weights[i] + ps[i-1];
            }
            int start = 1, end = n;
            int ans = n;
            while(start <= end){
                int mid = (start + end) / 2;
                int k = mid - start + 1;
                int expected = ps[mid] - ps[start - 1];
                int result = checker(k, start, mid);
                if(result > expected){
                    ans = mid; //we store the last element of SA as answer, just in case it is the answer
                               // important : we narrow down the ans to one element and store it, but in case like
                               //[1,2,3,4,5], hidden element : 3, query- ? 3 1 2 3, expected : 6, result : 7
                               //acc to our algo, we search ? 1 1 and ? 1 2 and both are equal to their expected value,
                               //our result was at idx : 3, which was stored when we got total as 7 and not 6.
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            System.out.println("! " + ans);
        }
    }
    public static int checker(int k, int p1, int p2){
        System.out.print("? " + k);
        for(int i=p1;i<=p2;i++){
            System.out.print(" " + i);
        }
        System.out.println();
        return sc.nextInt();
    }
}