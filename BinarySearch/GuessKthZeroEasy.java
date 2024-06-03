//https://codeforces.com/problemset/problem/1520/F1
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class GuessKthZeroEasy {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        int n = sc.nextInt();
        int t = sc.nextInt();
        int k = sc.nextInt();
        int start = 1, end = n;
        int ans = end;
        while(start <= end){
            int mid = (start + end) / 2;
            int total = query(start, mid); //total of range
            int ones = mid - start + 1; //total if all were 1s
            int zeros = ones - total; //no. of 0s in range
            if(zeros >= k){ //range has enough 0s and hence contains our answer
                ans = mid;
                end = mid - 1;
            }else{ //range doesn't have enough 0s
                k = k - zeros; // just need to look for the extra 0s
                start = mid + 1;
            }
        }
        System.out.println("! "+ans);
    }
    public static int query(int l, int r){
        System.out.println("? "+l+" "+r);
        return sc.nextInt();
    }
}
