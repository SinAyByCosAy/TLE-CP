package DPBootcamp;

import java.util.Scanner;

public class BookShopTabularDP {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] h = new int[n+1];
        int[] s = new int[n+1];
        for(int i=1;i<=n;i++){
            h[i] = sc.nextInt();
        }
        for(int i=1;i<=n;i++){
            s[i] = sc.nextInt();
        }
        int[][]dp = new int[n+1][x+1];
    }
}
