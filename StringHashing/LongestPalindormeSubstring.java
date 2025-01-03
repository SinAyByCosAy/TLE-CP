//given a string with one or more palindromic substrings, find the longest amongst them
package DPBootcamp.StringHashing;

import java.util.Scanner;

public class LongestPalindormeSubstring {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getLongestPalindromicSubstring(s));
    }
    public static String getLongestPalindromicSubstring(String s){
        int n = s.length();
        String rev = "";
        for(int i = n - 1; i >= 0; i--) rev += s.charAt(i);
        Hash a = new Hash(s);
        Hash b = new Hash(rev);

        Pair indexes = new Pair(0, 0);
        indexes = binarySearch(a, b, n, 1, indexes);
        indexes = binarySearch(a, b, n, 0, indexes);

        return s.substring(indexes.x, indexes.y + 1);
    }
    public static Pair binarySearch(Hash a, Hash b, int n, int category, Pair indexes){
        //category => 0: even, 1: odd
        int start = 1, end = n;
        while(start <= end){
            int mid = (start + end) / 2;
            if(mid % 2 == category || ++mid <= end){//if we want mid to be odd and we got that, great. Else ++mid
                Pair query = checkPalindromeSubstring(a, b, mid, n);
                if(query != null){//we found a palindrome
                    start = mid + 1; //look for a larger palindrome
                    if(query.y - query.x + 1 > indexes.y - indexes.x + 1)//found larger palindrome
                        indexes = query;
                }else end = mid - 1;//look in smaller range
            }else break;//nothing more to look for
        }
        return indexes;
    }
    public static Pair checkPalindromeSubstring(Hash a, Hash b, int len, int n){
        for(int i = 0; i < n; i++){
            if(i >= len - 1){
                int l = i - (len - 1), r = i;
                Hashes sHash = a.get(l, r);
                Hashes revHash = b.get(n - 1 - r, n - 1 - l);
                if(sHash.hash1 == revHash.hash1 && sHash.hash2 == revHash.hash2) return new Pair(l, r);
            }
        }
        return null;
    }
}
//TC: O(NlogN)