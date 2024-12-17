//https://codeforces.com/contest/2044/problem/D
package DPBootcamp.Contests.CF993Div4;

import java.util.Scanner;

public class ProbD {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            int[] res = new int[n];
            int[] nos = new int[n + 1];
            for(int i = 1; i <= n; i++)
                nos[i] = i;
            int idx = 1;
            for(int i = 0; i < n; i++){
                if(nos[arr[i]] != -1){
                    res[i] = arr[i];
                    nos[arr[i]] = -1;
                }else{
                    while(nos[idx] == -1) idx++;
                    res[i] = nos[idx];
                    nos[idx] = -1;
                }
            }
            for(int ele : res)
                System.out.print(ele + " ");
            System.out.println();
        }
    }
}
