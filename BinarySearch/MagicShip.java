//https://codeforces.com/contest/1117/problem/C
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class MagicShip {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long x2 = sc.nextLong();
        long y2 = sc.nextLong();
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();

        System.out.println(getDays(s, n, x1, y1, x2, y2));
    }
    public static long getDays(String s, int n, long x1, long y1, long x2, long y2){
        long start = 1, end = (long)1e15;
        long ans = -1;
        while(start <= end){
            long mid = (start + end) / 2;
            if(check(s, n, x1, y1, x2, y2, mid)){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
    public static boolean check(String s, int n, long x1, long y1, long x2, long y2, long days){
        long cyclesOfWind = days / n;
        long extraMovementTillIdx = days % n;
        long x3 = x1, y3 = y1;
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == 'L')
                x3 -= (i < extraMovementTillIdx) ? (cyclesOfWind + 1) : cyclesOfWind;
            else if(ch == 'R')
                x3 += (i < extraMovementTillIdx) ? (cyclesOfWind + 1) : cyclesOfWind;
            else if(ch == 'D')
                y3 -= (i < extraMovementTillIdx) ? (cyclesOfWind + 1) : cyclesOfWind;
            else
                y3 += (i < extraMovementTillIdx) ? (cyclesOfWind + 1) : cyclesOfWind;
        }
        long manhattanDistance = Math.abs(x2 - x3) + Math.abs(y2 - y3);
        return manhattanDistance <= days;
    }
}