//https://www.spoj.com/problems/BAISED/
package DPBootcamp.Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BiasedStandings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();
        while(t-- > 0){
            int n = sc.nextInt();
            sc.nextLine();
            Long[] ranks = new Long[n];
            for(int i = 0; i < n; i++){
                sc.next();
                long val = sc.nextLong();
                ranks[i] = val;
            }
            Arrays.sort(ranks);
            long bad = 0;
            for(int i = 0; i < n; i++)
                bad += Math.abs(ranks[i] - (i + 1));

            System.out.println(bad);
        }
    }
}
//TC: O(NlogN)