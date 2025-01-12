//https://codeforces.com/problemset/problem/1742/F
package DPBootcamp.Greedy;

import java.util.Scanner;

public class Smaller {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while(q-- > 0){
            int n = sc.nextInt();
            long sACount = 1;
            long tACount = 1;

            boolean higher = false;
            boolean sHigher = false;
            for(int i = 1; i <= n; i++){
                int d = sc.nextInt();
                int k = sc.nextInt();
                String sub = sc.next();
                if(higher) System.out.println("YES"); //once we found a char > 'a' in t, t > s always possible
                else {
                    for (int j = 0; j < sub.length(); j++) {
                        char ch = sub.charAt(j);
                        if (d == 1) {
                            if (ch > 'a') sHigher = true; //tracking if s has a char > 'a'
                            else sACount += k;//count of 'a'
                        } else {
                            if (ch > 'a') {//found char > 'a' in t, t > s always now
                                higher = true;
                                break;
                            } else tACount += k;
                        }
                    }
                    if (higher) System.out.println("YES"); //t > s
                    else if (sHigher) System.out.println("NO"); //t has all 'a's & s has char > 'a', s > t
                    else if (sACount < tACount) System.out.println("YES"); //both has only 'a's, compare count now
                    else System.out.println("NO");
                }
            }
        }
    }
}
//TC: O(N.|sub|)