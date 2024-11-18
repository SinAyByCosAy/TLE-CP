//https://codeforces.com/contest/587/problem/A
package DPBootcamp.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DuffAndWeightLifting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weight = new int[(int) 2e6 + 1];
        for(int i = 0; i < n; i++){
            int ele = Integer.parseInt(st.nextToken());
            weight[ele]++; //marking count of each power
        }
        int ans = 0;
        for(int i = 0; i < (int) 2e6; i++){
            if(weight[i] != 0){//there exists a power
                if((weight[i] & 1) == 1) ans++; //if the no of power is odd, then we'll be left with one power of it uncombined.
                // Hence, contributing to the answer

                weight[i + 1] += weight[i] / 2; //updating the count of the next power
            }
        }
        System.out.println(ans);
    }
}
//TC: O(2e6)