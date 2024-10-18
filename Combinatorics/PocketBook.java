//https://codeforces.com/problemset/problem/152/C
package DPBootcamp.Combinatorics;

import java.util.ArrayList;
import java.util.Scanner;

public class PocketBook {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        int mod = (int) 1e9 + 7;

        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i < m; i++)
            list.add(new int[26]);
        for(int i = 0; i < n; i++){//O(N.M)
            String s = sc.nextLine();
            for(int j = 0; j < m; j++)
                list.get(j)[s.charAt(j) - 'A']++;//creating a char map for each index
        }
        int ans = 1;
        for(int i = 0; i < m; i++){//O(26.M)
            int count = 0;
            int[] arr = list.get(i); //fetching the char map of each index
            for(int j = 0; j < 26; j++)
                if(arr[j] > 0) count ++; //found a distinct char
            ans = modMul(ans, count, mod); // ans = ans * count
        }
        System.out.println(ans);
    }
    public static int modMul(int x, int y, int mod){
        return (int)((x * 1l * y) % mod);
    }
}
//TC: O(N.M)