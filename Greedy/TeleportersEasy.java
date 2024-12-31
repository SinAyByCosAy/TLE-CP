//https://codeforces.com/problemset/problem/1791/G1
package DPBootcamp.Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class TeleportersEasy {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int c = sc.nextInt();
            Integer[] points = new Integer[n];
            for(int i = 0; i < n; i++) {
                points[i] = sc.nextInt();
                points[i] += (i + 1);
            }
            Arrays.sort(points);
            int count = 0;
            for(int ele : points){
                if(c - ele >= 0){
                    c -= ele;
                    count++;
                }else break;
            }
            System.out.println(count);
        }
    }
}
//TC: O(NlogN)