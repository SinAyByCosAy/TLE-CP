//https://codeforces.com/problemset/problem/1765/N
package DPBootcamp.Greedy;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class NumberReduction {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t-- > 0){
            String s = sc.nextLine();
            int k = sc.nextInt();
            sc.nextLine();
            int n = s.length();
            Deque<Integer>[] nums = new Deque[10];
            for(int i = 0; i < 10; i++) nums[i] = new LinkedList<>();
            for(int i = 0; i < n; i++){
                int ele = s.charAt(i) - '0';
                nums[ele].add(i);
            }
            int limit = n - k;
            int minIdx = -1;
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < limit; i++){
                int startIdx = minIdx + 1;//remaining chars start from min. idx + 1
                for(int j = (i == 0) ? 1 : 0; j < 10; j++){//for i = 0, we can't have 0 as a candidate
                    if(!nums[j].isEmpty() && nums[j].peekFirst() - startIdx <= k){ //we found smallest number in range
                        minIdx = nums[j].removeFirst();
                        res.append(j);
                        break;
                    }
                }
                k -= minIdx - startIdx; //#deletions = minIdx - start, then K = K - #deletions
                for(int j = 0; j < 10; j++){//remove all deleted indexes
                    while(!nums[j].isEmpty() && nums[j].peekFirst() < minIdx) nums[j].removeFirst();
                }
            }
            System.out.println(res);
        }
    }
}
//TC: O(N)