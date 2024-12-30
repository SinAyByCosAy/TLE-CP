package DPBootcamp.Contests.CF995Div3;

import java.util.Scanner;

public class ProbA {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = sc.nextInt();
            for (int i = 0; i < n; i++) {
                b[i] = sc.nextInt();
            }
            long sum = 0;
            for (int i = 0; i < n - 1; i++) {
                if (sum + a[i] - b[i + 1] >= sum) sum += a[i] - b[i + 1];
            }
            sum += a[n - 1];
            System.out.println(sum);
        }
    }
}
