package DPBootcamp.Contests.CF995Div3;

import java.util.Scanner;

public class ProbC {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int[] q = new int[m];
            int[] practice = new int[m];
            for(int i = 0; i < m; i++)
                q[i] = sc.nextInt();
            int unknown = -1;
            for(int i = 0; i < k; i++) {
                practice[i] = sc.nextInt();
                if(unknown == -1 && practice[i] != i + 1) unknown = i + 1;
            }
            if (k < n - 1) {
                for (int i = 1; i <= m; i++) System.out.print(0);
            } else if (k == n)
                for (int i = 1; i <= m; i++) System.out.print(1);
            else {
                for(int i = 0; i < m; i++){
                    if(q[i] != unknown) System.out.print(0);
                    else System.out.print(1);
                }
            }
            System.out.println();
        }
    }
}
