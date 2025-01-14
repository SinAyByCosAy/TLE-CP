//https://codeforces.com/problemset/problem/545/C
package DPBootcamp.Greedy;

import java.util.Scanner;

public class Woodcutters {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] pos = new int[n];
        int[] height = new int[n];
        for(int i = 0; i < n; i++){
            pos[i] = sc.nextInt();
            height[i] = sc.nextInt();
        }
        System.out.println(countTreesToCut(pos, height, n));
    }
    public static int countTreesToCut(int[] pos, int[] height, int n){
        if(n == 1 || n == 2) return n;
        int count = 2;
        for(int i = 1; i < n - 1; i++){
            if(pos[i] - height[i] > pos[i - 1])//can fall left
                count++;
            else if(pos[i] + height[i] < pos[i + 1]){//can fall right
                count ++;
                pos[i] += height[i];
            }
        }
        return count;
    }
}
//TC: O(N)