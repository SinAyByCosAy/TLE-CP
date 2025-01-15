//https://leetcode.com/problems/sum-of-scores-of-built-strings/description/
package DPBootcamp.StringHashing;

import java.util.Scanner;

public class ScoresOfBuiltStrings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(sumScores(s));
    }
    public static long sumScores(String s){
        int n = s.length();
        Hash a = new Hash(s);
        long score = 0;
        for(int i = 1; i <= n; i++)//all suffix lengths
            score += getScore(n, i, a);
        return score;
    }
    public static int getScore(int n, int len, Hash a){
        int start = 1, end = len;
        int ans = 0;
        while(start <= end){
            int mid = (start + end) / 2;
            Hashes pre = a.get(0, mid - 1); //prefix
            Hashes suf = a.get(n - len, n - len + (mid - 1)); //variable suffix
            if(pre.hash1 == suf.hash1 && pre.hash2 == suf.hash2){
                ans = mid;
                start = mid + 1;
            }else end = mid - 1;
        }
        return ans;
    }
}
//TC: O(NlogN)