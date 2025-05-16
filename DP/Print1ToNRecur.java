package DPBootcamp.DP;

import java.util.Scanner;

public class Print1ToNRecur {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        printNums(n, 0, "", true);
    }
    public static void printNums(String n, int idx, String numTillNow, boolean isPrefixSame){
        if(idx == n.length()){
            System.out.println(numTillNow);
            return;
        }

        for(char digit = '0'; digit <= (isPrefixSame ? n.charAt(idx) : '9'); digit++)
            printNums(n, idx + 1, numTillNow + digit, (isPrefixSame && n.charAt(idx) == digit));
    }
}
