//https://codeforces.com/contest/2049/problem/C
package DPBootcamp.Contests.CF994Div2;

import java.util.Scanner;

//break into two cycles from x-y edge. mark x = 0, y = 1, mark all other nodes in cycle as 0/1 alternately
//and if number of nodes in cycle is odd, mark the last node as 2
public class ProbC {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int[] arr = new int[n];
            arr[x - 1] = 0;
            arr[y - 1] = 1;
            int leftCycle = y - x + 1;
            for(int i = x; i < y - 1; i++){
                if(leftCycle % 2 == 1 && i == y - 2) arr[i] = 2;
                else arr[i] = arr[i - 1] ^ 1;
            }
            int rightCycle = n - leftCycle;
            int start = y;
            int count = 1;
            while(count <= rightCycle){
                int idx = start % n;
                if(rightCycle % 2 == 1 && count == rightCycle) arr[idx] = 2;
                else arr[idx] = arr[(idx - 1 + n) % n] ^ 1;
                start++;
                count++;
            }
            for(int ele : arr) System.out.print(ele + " ");
            System.out.println();
        }
    }
}
