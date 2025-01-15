//https://leetcode.com/problems/longest-happy-prefix/description/
package DPBootcamp.StringHashing;

import java.util.Scanner;

public class LongestHappyPrefix {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(longestPrefix(s));
    }
    public static String longestPrefix(String s){
        int n = s.length();
        Hash a = new Hash(s);
        int idx = -1;
        for(int i = 1; i < n; i++){
            Hashes prefix = a.get(0, i - 1);
            Hashes suffix = a.get(n - i, n - 1);
            if(prefix.hash1 == suffix.hash1 && prefix.hash2 == suffix.hash2) idx = i;
        }
        if(idx == -1) return "";
        else return s.substring(0, idx);
    }
}
//TC: O(N)