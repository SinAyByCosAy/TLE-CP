//https://codeforces.com/problemset/problem/1486/C1
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class GuessingGreatest {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        int n = sc.nextInt();
        int start = 1, end = n;
        while(end - start > 1){ // till the time we have 2 elements remaining
            int smax = checker(start, end); //finding second max in full range
            int mid = (start + end) / 2;
            if(smax <= mid){ //second max lies on the left of mid, so need to check on left
                if(checker(start, mid) == smax){ //deciding half to find max element
                    end = mid;
                }else{
                    start = mid;
                }
            }else{ //need to check on right
                if(checker(mid, end) == smax){
                    start = mid;
                }else{
                    end = mid;
                }
            }
        }
        int pos = checker(start, end); //in last two elements, the element not as second max is max
        if(pos == start)
            answer(end);
        else
            answer(start);
    }
    public static int checker(int start, int end){
        System.out.println("? "+start+" "+end);
        return sc.nextInt();
    }
    public static void answer(int ans){
        System.out.println("! "+ans);
    }
}
