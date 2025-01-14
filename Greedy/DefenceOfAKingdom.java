//https://www.spoj.com/problems/DEFKIN/
package DPBootcamp.Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class DefenceOfAKingdom {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int h = sc.nextInt();
            int w = sc.nextInt();
            int n = sc.nextInt();
            int[] row = new int[n + 2];
            int[] col = new int[n + 2];
            for(int i = 1; i <= n; i++){
                row[i] = sc.nextInt();
                col[i] = sc.nextInt();
            }
            row[n + 1] = h + 1; //right boundary
            col[n + 1] = w + 1;
            Arrays.sort(row);
            Arrays.sort(col);

            int maxRowSize = 0;
            int maxColSize = 0;
            for(int i = 1; i <= n + 1; i++){
                maxRowSize = Math.max(maxRowSize, row[i] - row[i - 1] - 1);
                maxColSize = Math.max(maxColSize, col[i] - col[i - 1] - 1);
            }
            System.out.println(maxRowSize * maxColSize);
        }
    }
}
//TC: O(NlogN)