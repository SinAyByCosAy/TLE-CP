package DPBootcamp;

import java.util.Scanner;

public class FrogTwo {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int height[] = new int[n];
        for(int i=0;i<n;i++) {
            height[i] = sc.nextInt();
        }
        System.out.println(minCost(height, k, n-1));
    }

    //brute recursion
    static int minCost(int[] h, int k, int stone){
        if(stone == 0)
            return 0;
        int ans = Integer.MAX_VALUE;
        for(int i=stone-1;i>=stone-k;i--){
            if(i<0)
                break;
            ans = Math.min(ans, Math.abs(h[stone] - h[i]) + minCost(h, k, i));
        }
        return ans;
    }
}
