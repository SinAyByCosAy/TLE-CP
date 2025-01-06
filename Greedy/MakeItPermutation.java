//https://codeforces.com/contest/1810/problem/C
package DPBootcamp.Greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class MakeItPermutation {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
            Arrays.sort(arr);
            long minCost = 1l * n * c + d; //remove all elements and add 1
            int duplicates = 0;
            HashSet<Integer> hs = new HashSet<>();
            for(int i = 0; i < n; i++){//finding min. cost among all costs to make array arr[i] permutation
                if(hs.contains(arr[i])) duplicates ++;
                else hs.add(arr[i]);

                long cost = 1l * duplicates * c; //cost to remove all duplicates
                cost += 1l * (arr[i] - (i + 1 - duplicates)) * d; //cost to add missing elements
                cost += 1l * (n - 1 - i) * c; //cost to remove remaining elements
                minCost = Math.min(minCost, cost);
            }
            System.out.println(minCost);
        }
    }
}
//TC: O(NlogN)