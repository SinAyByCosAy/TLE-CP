//https://codeforces.com/contest/723/problem/C
package DPBootcamp.Greedy;

import java.util.ArrayList;
import java.util.Scanner;

public class PolycarpAtTheRadio {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        int[] freq = new int[m + 1];
        ArrayList<Integer> uselessIndices = new ArrayList<>();
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            if(arr[i] > m) uselessIndices.add(i);
            else if(freq[arr[i]] == n / m) uselessIndices.add(i); //getting extra indices
            else freq[arr[i]]++; //counting freq of [1, m] bands
        }
        int idx = 0;
        int changes = 0;
        for(int i = 1; i <= m; i++){
            if(freq[i] < n / m){
                while(freq[i] < n / m){
                    arr[uselessIndices.get(idx++)] = i; //picking an extra index to replace with favourable band
                    freq[i]++;
                    changes++;
                }
            }
        }
        System.out.println((n / m) + " " + changes);
        for(int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
//TC: O(N)