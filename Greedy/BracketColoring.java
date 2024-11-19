//https://codeforces.com/contest/1837/problem/D
package DPBootcamp.Greedy;

import java.util.Scanner;

public class BracketColoring {
    public static void main(String[] argsq){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();

            int sum = 0;
            boolean pos = true; //sum positive only - check
            boolean neg = true; //sum negative only - check
            for(char ch : s.toCharArray()){
                if(ch == '(') sum += 1;
                else sum -= 1;
                //if the string has both positive and negative sum then it requires 2 colors, else 1
                if(sum < 0) pos = false;
                else if(sum > 0) neg = false;
            }
            if(sum != 0) System.out.println(-1); //if sum != 0, then brackets are not balanced
            else{
                if(pos || neg){ //only 1 color is required
                    System.out.println(1);
                    for(int i = 1; i <= n; i++)
                        System.out.print(1 + " ");
                }else{ //2 colors are required
                    System.out.println(2);
                    int sum1 = 0, sum2 = 0;
                    for(char ch : s.toCharArray()){
                        int color;
                        if(ch == '('){
                            if(sum2 < 0){
                                sum2 += 1;
                                color = 2;
                            }else{
                                sum1 += 1;
                                color = 1;
                            }
                        }else{
                            if(sum1 > 0){
                                sum1 -= 1;
                                color = 1;
                            }else{
                                sum2 -= 1;
                                color = 2;
                            }
                        }
                        System.out.print(color + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
//TC: O(N)