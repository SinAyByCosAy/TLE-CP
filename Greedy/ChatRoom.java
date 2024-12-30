//https://codeforces.com/problemset/problem/58/A
package DPBootcamp.Greedy;

import java.util.Scanner;

public class ChatRoom {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int h = 0, e = 0, l = 0, o = 0;
        boolean flag = false;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == 'h') h++;
            else if(ch == 'e' && h > 0) e++;
            else if(ch == 'l' && h > 0 && e > 0) l++;
            else if(ch == 'o' && h > 0 && e > 0 && l > 1){
                flag = true;
                break;
            }
        }
        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }
}
//TC: O(N)