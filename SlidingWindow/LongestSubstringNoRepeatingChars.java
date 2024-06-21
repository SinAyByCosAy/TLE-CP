//https://leetcode.com/problems/longest-substring-without-repeating-characters/
package DPBootcamp.SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class LongestSubstringNoRepeatingChars {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int l = 0, r = 0;
        int maxLen = 0;
        int[] map = new int[256]; //map for 256 ASCII chars
        Arrays.fill(map, -1);
        while(r < n){
            char curr = s.charAt(r);
            if(map[curr] != -1 && map[curr] >= l){ //if we have seen the char before and if it's present in the current range
                l = map[curr] + 1; //we shift our left end forward to remove duplication and look for a better answer
            }
            int len = r - l + 1;
            maxLen = Math.max(maxLen, len);
            map[curr] = r; //update current position
            r++;
        }
        System.out.println(maxLen);
    }
}
