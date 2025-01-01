//given Q queries: (l, r) for a string S
//check if the range has a palindrome string
package DPBootcamp.StringHashing;

import java.util.Scanner;

public class CheckSubStringPalindrome {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();
        int n = s.length();
        String rev = "";
        for(int i = n - 1; i >= 0; i--) rev += s.charAt(i);

        Hash a = new Hash(s); //hash of original string
        Hash b = new Hash(rev); //hash of reversed string

        int q = sc.nextInt();//queries
        for(int i = 1; i <= q; i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            Hashes sHash = a.get(l, r);
            Hashes revHash = b.get(n - 1 - r, n - 1 - l);
            if(sHash.hash1 == revHash.hash1 && sHash.hash2 == revHash.hash2) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}
//TC: O(N + Q);