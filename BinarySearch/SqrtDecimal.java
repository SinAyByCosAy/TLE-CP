package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class SqrtDecimal {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        double precision = 1e-10;
        double start = 1, end = x, mid;
        double ans = 1;
        for(int cnt = 0; cnt <= 100; cnt++){
            mid = (start + end)/2;
            if(mid <= (x/mid)){
                ans = mid;
                start = mid + precision;
            }else{
                end = mid - precision;
            }
        }
        System.out.println(ans);
    }
}



