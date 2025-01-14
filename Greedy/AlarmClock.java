//https://codeforces.com/problemset/problem/898/D
package DPBootcamp.Greedy;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class AlarmClock {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        Integer[] time = new Integer[n];
        for(int i = 0; i < n; i++) time[i] = sc.nextInt();
        Arrays.sort(time);

        Deque<Integer> currAlarms = new LinkedList<>();
        int alarmOffCount = 0;
        for(int i = 0; i < n; i++){
            while(!currAlarms.isEmpty() && time[i] >= currAlarms.peekFirst() + m)
                currAlarms.removeFirst(); //remove out of window elements
            if(currAlarms.size() == k - 1) alarmOffCount++; //turn off current alarm
            else currAlarms.add(time[i]); //accept current alarm
        }
        System.out.println(alarmOffCount);
    }
}
//TC: O(N)