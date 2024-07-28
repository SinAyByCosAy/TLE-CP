//Given an array, find the length of the longest SA with not more than K distinct elements
// ex - [1 2 3 1 3 2 4 5 2 1 1] k = 4
//   => 7
package DPBootcamp.TwoPointers;

import java.util.HashMap;
import java.util.Scanner;

public class LengthOfLongestSAWIthAtmostKDistinctEle {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int ans = 0;
        int i = 0, j = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        while(j < n){// looking from right end's POV
            hm.put(arr[j], hm.getOrDefault(arr[j], 0) + 1);//adding new element to the SA, right pointer moved forward
            while(i <= j && hm.size() > k){//shifting the left end forward till #distinct ele > k
                hm.put(arr[i], hm.get(arr[i]) - 1);
                if(hm.get(arr[i]) == 0)
                    hm.remove(arr[i]);
                i++;
            }
            ans = Math.max(ans, j - i + 1); //we have a good SA segment
            j++;//move to the next element on the right end
        }
        System.out.println(ans);
    }
}
