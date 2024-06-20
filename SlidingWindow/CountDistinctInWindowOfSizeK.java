package DPBootcamp.SlidingWindow;

import java.util.HashMap;
import java.util.Scanner;

public class CountDistinctInWindowOfSizeK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < n; i++){
            //add value by increasing it's count
            hm.put(arr[i], hm.getOrDefault(hm.get(arr[i]), 0) + 1);

            //reduce the count of the element at the start of the window
            if(i >= k){
                hm.put(arr[i - k], hm.get(arr[i - k]) - 1);
                if(hm.get(arr[i - k]) == 0)//if count becomes 0, remove the element
                    hm.remove(arr[i - k]);
            }

            if(i >= k - 1)//distinct elements are all the elements in map
                System.out.print(hm.size()+" ");
        }
        System.out.println();
    }
}
