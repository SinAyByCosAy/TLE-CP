//https://leetcode.com/problems/longest-duplicate-substring/description/
package DPBootcamp.StringHashing;

import java.util.HashSet;
import java.util.Scanner;

public class LongestDuplicateSubstring {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(longestDupSubString(s));
    }
    public static String longestDupSubString(String s){
        Hash a = new Hash(s);
        int start = 0, end = s.length() - 1;
        Pair indexes = new Pair(0, 0);
        while(start <= end){//BS on answer : O(log N)
            int mid = (start + end) / 2;
            Pair query = getDupForLength(s, a, mid);
            if(query != null){
                start = mid + 1;
                indexes = query;
            }else end = mid - 1;
        }
        String res = s.substring(indexes.x, indexes.y + 1);
        return res;
    }
    public static Pair getDupForLength(String s, Hash a, int len){
        int n = s.length();
        HashSet<Integer> hashValue = new HashSet<>();
        for(int i = 0; i < n; i++){//O(N)
            if(i >= len - 1){
                Hashes sHash = a.get(i - (len - 1), i);
                if(hashValue.contains(sHash.hash1) && hashValue.contains(sHash.hash2)) return new Pair(i - (len - 1), i);
                hashValue.add(sHash.hash1);
                hashValue.add(sHash.hash2);
            }
        }
        return null;
    }
}
class Pair{
    int x, y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
//TC: O(NlogN)