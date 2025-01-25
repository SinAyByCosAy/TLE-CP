//https://codeforces.com/contest/2049/problem/A
package DPBootcamp.Contests.CF994Div2;

import java.util.ArrayList;
import java.util.Scanner;

public class ProbA {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < n; i++){
                if(arr[i] > 0) list.add(i);
            }
            if(list.size() == 0) System.out.println(0);
            else if(list.get(list.size() - 1) - list.get(0) + 1 == list.size()) System.out.println(1);
            else System.out.println(2);
        }
    }
}
