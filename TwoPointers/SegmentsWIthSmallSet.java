//https://codeforces.com/edu/course/2/lesson/9/2/practice/contest/307093/problem/E
package DPBootcamp.TwoPointers;

import java.util.HashMap;
import java.util.Scanner;

public class SegmentsWIthSmallSet {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int i = 0, j = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        long count = 0;
        while(j < n){
            hm.put(arr[j], hm.getOrDefault(arr[j], 0) + 1);
            while(i <= j && hm.size() > k){
                hm.put(arr[i], hm.get(arr[i]) - 1);
                if(hm.get(arr[i]) == 0)
                    hm.remove(arr[i]);
                i++;
            }
            count += (j - i + 1);
            j++;
        }
        System.out.println(count);
    }
}