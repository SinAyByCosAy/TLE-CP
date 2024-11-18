//https://codeforces.com/problemset/problem/1332/C
package DPBootcamp.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KCompleteWord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            String s = br.readLine();
            int sections = n / k;
            int p1 = 0, p2 = k - 1;
            int ans = 0;
            while (p1 <= p2) {//traversing every char in palindrome fashion, exactly once
                int[] freq = new int[26];
                int max = 0, totalChars = 0;
                char ch;
                for (int i = 0; i < sections; i++) {//to jump over sections and get to the indices
                    ch = s.charAt(p1 + (k * i));
                    freq[ch - 'a']++;
                    max = Math.max(max, freq[ch - 'a']);//finding the max freq. of chars at current in-focus positions
                    totalChars++;
                    if (p1 != p2) {//same char shouldn't be added again or counted again
                        ch = s.charAt(p2 + (k * i));
                        freq[ch - 'a']++;
                        max = Math.max(max, freq[ch - 'a']);
                        totalChars++;
                    }
                }
                ans += (totalChars - max); //converting the remaining chars to the max one
                p1++;
                p2--;
            }
            System.out.println(ans);
        }
    }
}
//TC: O(N)