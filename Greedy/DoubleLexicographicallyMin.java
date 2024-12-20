//https://codeforces.com/problemset/problem/1799/C
package DPBootcamp.Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class DoubleLexicographicallyMin {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t-- > 0){
            char[] arr = sc.nextLine().toCharArray();
            Arrays.sort(arr);
            int n = arr.length;
            char[] res = new char[n];
            int p1 = 0, p2 = n - 1;
            int idx = 0;
            while(idx < n){
                if(idx == n - 1) {
                    res[p1] = arr[n - 1];
                    idx++;
                }
                else if(arr[idx] == arr[idx + 1]){
                    res[p1++] = arr[idx];
                    res[p2--] = arr[idx];
                    idx += 2;
                }else{
                    if(arr[idx + 1] == arr[n - 1]){
                        int k = (n - 1) - (idx + 1) + 1;
                        for(int i = 1; i <= (k + 2 - 1) / 2; i++)
                            res[p1++] = arr[idx + 1];
                        res[p1++] = arr[idx];
                        for(int i = 1; i <= k / 2; i++)
                            res[p1++] = arr[idx + 1];
                        break;
                    }else{
                        res[p1++] = arr[idx + 1];
                        res[p2--] = arr[idx];
                        for(int i = idx + 2; i < n; i++)
                            res[p1++] = arr[i];
                        break;
                    }
                }
            }
            System.out.println(String.valueOf(res));
        }
    }
}
