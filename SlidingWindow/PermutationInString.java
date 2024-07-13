//https://leetcode.com/problems/permutation-in-string/
package DPBootcamp.SlidingWindow;

import java.util.Scanner;

public class PermutationInString {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(checkInclusion(s1, s2));
    }
    public static boolean checkInclusion(String s1, String s2){
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        int k = s1.length();
        for(int i = 0; i < k; i++){
            s1Count[s1.charAt(i) - 97]++;//map s1
        }
        int n = s2.length();
        for(int i = 0; i < n; i++){
            s2Count[s2.charAt(i) - 97]++;//add elements of s2 in window

            if(i >= k){
                s2Count[s2.charAt(i - k) - 97]--;//remove element
            }
            if(i >= k - 1){
                boolean flag = true;
                for(int j = 0; j < 26; j++){//compare if all elements in s1 and s2 are same
                    if(s1Count[j] != s2Count[j]){
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    return true;
            }
        }
        return false;
    }
}
