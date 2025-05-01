//https://zibada.guru/gcj/ks2022a/problems/#C
package DPBootcamp.DP;

import java.util.HashMap;
import java.util.Scanner;

public class PalindromeFreeStrings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();
            HashMap<String, Boolean>[] dp = new HashMap[n];
            for (int i = 0; i < n; i++) dp[i] = new HashMap<>();
            if (n <= 4) System.out.println("POSSIBLE");
            else System.out.println(checkPalindromeFree(dp, 0, "", s) ? "POSSIBLE" : "IMPOSSIBLE");
        }
    }
    public static boolean checkPalindromeFree(HashMap<String, Boolean>[] dp, int idx, String suffix, String s){
        suffix = trim(suffix);
        if(idx == s.length()) return true;
        if(dp[idx].containsKey(suffix)) return dp[idx].get(suffix);

        //check if the new substrings are not palindrome and get the transition value
        boolean one = isPalindrome(suffix + "1") && checkPalindromeFree(dp, idx + 1, suffix + "1", s);
        boolean zero = isPalindrome(suffix + "0") && checkPalindromeFree(dp, idx + 1, suffix + "0", s);

        if(s.charAt(idx) == '1') dp[idx].put(suffix, one);
        else if(s.charAt(idx) == '0') dp[idx].put(suffix, zero);
        else dp[idx].put(suffix, one || zero);

        return dp[idx].get(suffix);
    }
    public static String trim(String sub){
        return sub.substring(Math.max(0, sub.length() - 6), sub.length());//if len <= 5, return the whole string. else return the last 6 chars
    }
    public static boolean isPalindrome(String suffix){
        if(suffix.length() >= 5 && compare(suffix, 5)) return false; //false if we do have a palindrome
        if(suffix.length() >= 6 && compare(suffix, 6)) return false;
        return true;
    }
    public static boolean compare(String sub, int len){//compare for palindrome
        int i = sub.length() - len, j = sub.length() - 1;
        while(i <= j){
            if(sub.charAt(i) != sub.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}