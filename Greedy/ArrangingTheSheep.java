//https://codeforces.com/contest/1520/problem/E
package DPBootcamp.Greedy;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrangingTheSheep {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();

            ArrayList<Integer> pos = new ArrayList<>();
            for(int i = 0; i < n; i++)
                if(s.charAt(i) == '*') pos.add(i); //add the positions of sheep

            if(pos.size() == 0) System.out.println(0);
            else {
                int median = pos.get(pos.size() / 2); //median position of the sheeps
                long ans = 0;
                for (int i = 0; i < pos.size(); i++) {
                    int curr = pos.get(i);
                    int sheepPos = Math.abs(pos.size() / 2 - i); //pointer of how far we need to place current sheep from median
                    if(i < pos.size() / 2) ans += median - sheepPos - curr;
                    else if(i > pos.size() / 2) ans += curr - (median + sheepPos);
                }
                System.out.println(ans);
            }
        }
    }
}
//TC: O(N)