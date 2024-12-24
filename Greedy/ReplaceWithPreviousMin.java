//https://codeforces.com/contest/1675/problem/E
package DPBootcamp.Greedy;

import java.util.Scanner;

public class ReplaceWithPreviousMin {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();
            int cost = k, rem = k;
            char largest = 'a', secondLargest = 'a';
            char prevChar = 'a';
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < n; i++){
                char ch = s.charAt(i);
                if(ch == 'a'){ res.append('a'); continue; }
                if(ch > largest){
                    if(ch - 'a' <= cost){
                        secondLargest = largest;
                        largest = ch;
                        res.append('a');
                        rem = cost - (ch - 'a');
                    }else if(rem > 0){
                        secondLargest = largest;
                        largest = ch;
                        prevChar = (char)(ch - rem);
                        res.append(prevChar);
                        rem = 0;
                    }else{
                        res.append(ch);
                    }
                }else if(ch <= largest && ch > secondLargest){
                    if(ch >= prevChar) res.append(prevChar);
                    else res.append(ch);
                }else{
                    res.append('a');
                }
            }
            System.out.println(res);
        }
    }
}
//TC:O(N)