//https://codeforces.com/problemset/problem/1486/C2
package DPBootcamp.BinarySearch;

import java.util.Scanner;

//MAX Queries allowed = 20
public class GuessingGreatestHard {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        int n = sc.nextInt();
        int smax = checker(1, n);

        if(smax != 1 && checker(1, smax) == smax){//max is present on left of second max
            int start = 1, end = smax - 1;
            int ans = start;
            while(start <= end){//binary search to find the maximum left range such that query(left, smax) = smax
                int mid = (start + end) / 2;
                int temp = checker(mid, smax);
                if(temp == smax){
                    ans = mid;
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }
            answer(ans);
        }else{//max is present on the right of the second max
            int start = smax + 1, end = n;
            int ans = end;
            while(start <= end){//binary search to find the minimum right range such that query(smax, right) = smax
                int mid = (start + end) / 2;
                int temp = checker(smax, mid);
                if(temp == smax){
                    ans = mid;
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            answer(ans);
        }
    }
    public static int checker(int start, int end){
        System.out.println("? "+start+" "+end);
        return sc.nextInt();
    }
    public static void answer(int ans){
        System.out.println("! "+ans);
    }
}
