package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class SqrtDecimal {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        double x = sc.nextInt();
        double precision = 1e-7;
        double start = 1, end = x, mid;
        double ans = 1;
        while(end - start > precision){
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



