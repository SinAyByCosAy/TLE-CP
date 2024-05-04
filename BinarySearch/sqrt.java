package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class sqrt {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int s = 1, e = x, m;
        int ans = 1;
        while(s <= e){
            m = (s + e)/2;
            if(m <= (x/m)){
                ans = m;
                s = m + 1;
            }else{
                e = m - 1;
            }
        }
        System.out.println(ans);
    }
}
