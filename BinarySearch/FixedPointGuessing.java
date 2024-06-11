//https://codeforces.com/problemset/problem/1698/D
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class FixedPointGuessing {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int start = 1, end = n;
            int ans = end;
            while(start <= end){
                int mid = (start + end) / 2;
                int count = checker(start, mid);
                if(count % 2 == 0){// if #elements with value in range [l,r] even, fixed number is outside range
                    start = mid + 1;
                }else{// if count is odd, number is in our current range
                    ans = mid;
                    end = mid - 1;
                }
            }
            System.out.println("! "+ans);
        }
    }
    public static int checker(int start, int end){
        System.out.println("? "+start+" "+end);
        int count = 0;
        for(int i=start; i<=end; i++){
            int ele = sc.nextInt();
            if(ele >= start && ele <= end)
                count++;
        }
        return count;
    }
}
