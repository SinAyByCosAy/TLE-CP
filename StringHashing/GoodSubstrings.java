//https://codeforces.com/contest/271/problem/D
package DPBootcamp.StringHashing;

import java.util.HashSet;
import java.util.Scanner;

public class GoodSubstrings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] status = sc.nextLine().toCharArray();
        int n = s.length();
        int k = sc.nextInt();

        Hash a = new Hash(s);
        long ans = 0;
        for(int i = 1; i <= n; i++) ans += getValidSubstrings(s, status, n, k, a, i);

        System.out.println(ans);
    }
    public static int getValidSubstrings(String s, char[] status, int n, int k, Hash a, int size){
        int countBad = 0;
        HashSet<String> hs = new HashSet<>();
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);//new element in window
            if(status[ch - 'a'] == '0') countBad++;
            if(i >= size){ //out of window element
                char rem = s.charAt(i - size);
                if(status[rem - 'a'] == '0') countBad --;
            }
            if(i >= size - 1){ //check for answer
                if(countBad <= k) {
                    Hashes sub = a.get(i - size + 1, i);
                    hs.add(sub.hash1 + "," + sub.hash2);
                }
            }
        }
        return hs.size();
    }
}
//TC: O(N^2)