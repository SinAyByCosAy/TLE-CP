//find kth string formed in dictionary order from the letters of the input string
//not taking mod in this problem
//ex - aaaabbcccd, k = 120 => aaabcbcdac
package DPBootcamp.Combinatorics;

import java.util.Scanner;

public class KthDictionaryString {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = sc.nextInt();

        int n = s.length();
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for(int i = 1; i <= n; i++) //store all factorials from 1! to n!
            factorial[i] = factorial[i - 1] * i;

        int[] freq = new int[26];
        for(char ch : s.toCharArray()) //mark the freq of each char for arrangements
            freq[ch - 'a']++;

        StringBuffer res = new StringBuffer(); //storing the result
        for(int i = 0; i < n; i++){ //start filling each index of the string
            for(int selectIdx = 0; selectIdx < 26; selectIdx++){ // looking for candidate char in dictionary order
                if(freq[selectIdx] > 0){ //found a candidate
                    freq[selectIdx]--; //picking it for testing the prefix
                    int noOfStrings = factorial[n - res.length() - 1]; //total arrangements of the remaining chars
                    for(int groups = 0; groups < 26; groups++)
                        noOfStrings /= factorial[freq[groups]]; //removing duplicate permutations
                    if(noOfStrings >= k){ //our candidate is correct, we can't have a larger char
                        res.append((char) (selectIdx + 'a'));
                        break; //need to move to the next index
                    }
                    //we need a bigger candidate
                    k -= noOfStrings; //we skip past all the strings made by the current candidate prefix
                    freq[selectIdx]++; //rejecting the current candidate and adding it back
                }
            }
        }
        System.out.println(res);
    }
}
//TC: O(N.26.26)