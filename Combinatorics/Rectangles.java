//https://codeforces.com/problemset/problem/844/B
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class Rectangles {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] rowOnes = new int[n];
        int[] rowZeros = new int[n];
        int[] colOnes = new int[m];
        int[] colZeros = new int[m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int ele = sc.nextInt();
                if(ele == 1){
                    rowOnes[i]++;
                    colOnes[j]++;
                }else{
                    rowZeros[i]++;
                    colZeros[j]++;
                }
            }
        }
        //same logic for one and zero
        //say count of 1 = k
        //row sets = 2^k - 1
        //col sets = 2^k - 1 - k
        long ans = 0;
        for(int i = 0; i < n; i++){//we could compute row values during input as well
            ans += (1l << rowOnes[i]) - 1;
            ans += (1l << rowZeros[i]) - 1;
        }
        for(int i = 0; i < m; i++){
            ans += (1l << colOnes[i]) - 1 - colOnes[i];
            ans += (1l << colZeros[i]) - 1 - colZeros[i];
        }
        System.out.println(ans);
    }
}
//TC: O(N + M)