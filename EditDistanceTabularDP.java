package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class EditDistanceTabularDP {
    static int dp[][];
    static String s1, s2;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        s1 = sc.nextLine();
        s2 = sc.nextLine();
        int n = s1.length();
        int m = s2.length();
        dp = new int[n][m];
        for (int[] row : dp)
            Arrays.fill(row, -1);
//        System.out.println(ed(n - 1, m - 1));
    }
}