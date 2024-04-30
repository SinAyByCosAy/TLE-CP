package DPBootcamp.DPBootcamp;

import java.util.Scanner;

public class ByElevatorOrStairs {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int a[] = new int[n-1];
        int b[] = new int[n-1];
        for(int i=0;i<n-1;i++){
            a[i] = sc.nextInt();
        }
        for(int i=0;i<n-1;i++){
            b[i] = sc.nextInt();
        }
        int result[] = new int[n];
        for(int i=1;i<n;i++){
            result[i] = result[i-1] + Math.min(a[i-1], b[i-1] + c);
        }
        for(int ele:result){
            System.out.print(ele+" ");
        }
    }
}
