//https://codeforces.com/contest/1766/problem/C
package DPBootcamp;

import java.util.Arrays;
import java.util.Scanner;

public class HamiltonianWall {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int m = sc.nextInt();
            sc.nextLine();
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            int count = 0;
            for(int i=0;i<m;i++){
                if(s1.charAt(i) == 'B')
                    count ++;
                if(s2.charAt(i) == 'B')
                    count ++;
            }
            int row1 = 0;
            int row2 = 0;
            for(int i=0;i<m;i++){
                boolean flag = true;
                if(s1.charAt(i) == 'W') {
                    row1 = 0;
                    flag = false;//if we have white in a column, we don't need to compare up and down
                }
                else {
                    row1 = row1 + 1;//get number of Bs in line before and add the current one(+1)
                }
                if(s2.charAt(i) == 'W') {
                    row2 = 0;
                    flag = false;
                }
                else {
                    row2 = row2 + 1;
                }
                if(flag) {//form a path for up down
                    if (row1 == row2) {
                        row1++;
                        row2++;
                    } else if (row1 > row2) {
                        row2 = row1 + 1;
                    } else {
                        row1 = row2 + 1;
                    }
                }
            }
            int ans = Math.max(row1, row2);
            if(ans == count)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
