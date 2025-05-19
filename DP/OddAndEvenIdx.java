package DPBootcamp.DP;

import java.util.Scanner;

//Find how many nums from 0 to N(<=1e18) have odd digit at the odd idx and even digit at the even idx(from right to left)
public class OddAndEvenIdx {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int[][][] dp = new int[num.length()][2][2];
        for(int i = 0; i < num.length(); i++) for(int j = 0; j <= 1; j++) for(int k = 0; k <= 1; k++) dp[i][j][k] = -1;
        System.out.println(countNums(dp, num, 0, 1, 1));
    }
    public static int countNums(int[][][] dp, String num, int idx, int leadingZero, int tight){
        int n = num.length();
        if(idx == n) return 1;
        if(dp[idx][leadingZero][tight] != -1) return dp[idx][leadingZero][tight];

        int count = 0;
        int expectedIdx = n - 1 - idx;
        for(int i = 0; i <= (tight == 1 ? num.charAt(idx) - '0' : 9); i++){
            //Zeros acceptable on any index if they are leading zeros
            if(i == 0 && leadingZero == 1) count += countNums(dp, num, idx + 1, 1, 0);
            //valid condition, no leading zero
            else if(i % 2 == expectedIdx % 2) count += countNums(dp, num, idx + 1, 0, (tight == 1 && num.charAt(idx) - '0' == i) ? 1 : 0);
        }
        return dp[idx][leadingZero][tight] = count;
    }
}
//TC: O(log10(N) * 2 * 2)