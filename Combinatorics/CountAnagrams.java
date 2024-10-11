//https://leetcode.com/problems/count-anagrams/
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class CountAnagrams {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(countAnagrams(s));
    }
    public static int countAnagrams(String s){
        int n = s.length();
        s += " ";
        StringBuilder word = new StringBuilder();
        int mod = (int) 1e9 + 7;
        int[] factorial = getFactorial(n, mod); //O(N)
        int[] invFactorial = getInvFactorial(n, factorial, mod); //O(N)
        int ans = 1;
        for(char ch : s.toCharArray()){ //O(N), only one iteration of full string
            if(ch != ' ')
                word.append(ch);
            else{
                ans = modMul(ans, getPermutations(word, factorial, invFactorial, mod), mod);
                word.setLength(0);
            }
        }
        return ans;
    }
    public static int getPermutations(StringBuilder s, int[] factorial, int[] invFactorial, int mod){
        int n = s.length();
        int res = factorial[n];
        int[] freq = new int[26];
        for(char ch : s.toString().toCharArray()) //O(N), only one iteration of string word by word
            freq[ch - 'a']++;
        for(int i = 0; i < 26; i++) { //O(26), only repetitive work word by word
            if(freq[i] > 1)
                res = modMul(res, invFactorial[freq[i]], mod);
        }
        return res;
    }
    public static int[] getFactorial(int limit, int mod){ //O(N)
        int[] factorial = new int[limit + 1];
        factorial[0] = 1;
        for(int i = 1; i <= limit; i++)
            factorial[i] = modMul(factorial[i - 1], i, mod);
        return factorial;
    }
    public static int[] getInvFactorial(int limit, int[] factorial, int mod){ //O(N)
        int[] invFactorial = new int[limit + 1];
        invFactorial[limit] = mmInvPrime(factorial[limit], mod);
        for(int i = limit - 1; i >= 0; i--)
            invFactorial[i] = modMul(invFactorial[i + 1], i + 1, mod);
        return invFactorial;
    }
    public static int mmInvPrime(int a, int m){ //O(log m)
        int b = m - 2;
        int res = 1;
        while(b > 0){
            if((b & 1) == 1) res = modMul(res, a, m);
            a = modMul(a, a, m);
            b >>= 1;
        }
        return res;
    }
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
}
//TC: O(26.N)