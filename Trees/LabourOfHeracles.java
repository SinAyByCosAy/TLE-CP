//https://codeforces.com/problemset/problem/1466/D
package DPBootcamp.Trees;

import java.util.Arrays;
import java.util.Scanner;

public class LabourOfHeracles {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] weights = new int[n + 1];
            for(int i = 1; i <= n; i++) weights[i] = sc.nextInt();
            int[] degree = new int[n + 1];
            for(int i = 1; i < n; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                //we don't need adjacency list, we just need count of edges at each node
                degree[a]++;
                degree[b]++;
            }

            //array to insert (n-2) weights based on their degree count
            Integer[] increaseByWeight = new Integer[n - 1];
            increaseByWeight[0] = 0;
            int idx = 1;
            for(int i = 1; i <= n; i++){
                int count = degree[i];
                for(int j = 1; j <= count - 1; j++) increaseByWeight[idx++] = weights[i];
            }
            Arrays.sort(increaseByWeight);

            long sumK = 0;
            for(int ele : weights) sumK += ele; //sum for K = 1
            System.out.print(sumK+" ");
            for(int i = n - 2; i >= 1; i--) {//sum for other K values
                sumK += increaseByWeight[i];
                System.out.print(sumK + " ");
            }
            System.out.println();
        }
    }
}
//TC: O(NlogN)