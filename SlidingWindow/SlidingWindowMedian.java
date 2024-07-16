//CSES and Leetcode has different o/p expectation
package DPBootcamp.SlidingWindow;

import java.util.Scanner;
import java.util.TreeMap;

public class SlidingWindowMedian {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int k = sc.nextInt();
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        TreeMap<Long, Integer> smaller = new TreeMap<>();
        TreeMap<Long, Integer> larger = new TreeMap<>();
        int limit1 = (k + 1) / 2;
        int sCount = 0;
        for(int i = 0; i < n; i++) {
            long ele = arr[i];//adding element
            if (sCount < limit1) {//smaller is vacant
                smaller.put(ele, smaller.getOrDefault(ele, 0) + 1);
                sCount++;
            } else {
                if (ele < smaller.lastKey()) {//curr ele belongs in smaller and last ele belongs in larger
                    long rem = smaller.lastKey();
                    smaller.put(ele, smaller.getOrDefault(ele, 0) + 1);
                    smaller.put(rem, smaller.get(rem) - 1);
                    larger.put(rem, larger.getOrDefault(rem, 0) + 1);
                    if (smaller.get(rem) == 0) smaller.remove(rem);
                } else {//curr ele belongs in larger
                    larger.put(ele, larger.getOrDefault(ele, 0) + 1);
                }
            }

            if (i >= k) {
                long rem = arr[i - k];//removing element
                if (smaller.containsKey(rem)) {//remove from smaller
                    smaller.put(rem, smaller.get(rem) - 1);
                    if (smaller.get(rem) == 0) smaller.remove(rem);
                    long fetch = larger.firstKey();//fetch smallest from larger
                    larger.put(fetch, larger.get(fetch) - 1);
                    if (larger.get(fetch) == 0) larger.remove(fetch);
                    smaller.put(fetch, smaller.getOrDefault(fetch, 0) + 1);//put it in smaller
                } else {//remove from larger
                    larger.put(rem, larger.get(rem) - 1);
                    if (larger.get(rem) == 0) larger.remove(rem);
                }
            }

            if (i >= k - 1) {//output
                System.out.print(smaller.lastKey()+" ");
            }
        }
        System.out.println();
    }
}