package DPBootcamp.SlidingWindow;

import java.util.HashMap;
import java.util.Scanner;

public class LongestSubstringNoRepeatingChars {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        HashMap<Character, Integer> hm = new HashMap<>();
        int p1 = 0;
        int maxLen = 0;
        for(int p2 = 0; p2 <= n; p2++){
            if(p2 == n){
                maxLen = Math.max(maxLen, p2 - p1);

            }else if(hm.containsKey(s.charAt(p2))){
                if(hm.get(s.charAt(p2)) < p1)
                    hm.put(s.charAt(p2), p2);
                else {
                    maxLen = Math.max(maxLen, p2 - p1);
                    p1 = hm.get(s.charAt(p2)) + 1;
                    hm.put(s.charAt(p2), p2);
                }
            }else{
                hm.put(s.charAt(p2), p2);
            }
        }
        System.out.println(maxLen);
    }
}
