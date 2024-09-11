//https://codeforces.com/contest/876/problem/B
package DPBootcamp.NumberTheory;

import java.util.HashMap;
import java.util.Scanner;

//elements that have same % m value will have difference divisible by m
public class DivisibilityOfDifferences {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        HashMap<Integer, Integer> hm = new HashMap<>(); //map to store count of elements with same mod value
        boolean possible = false;
        for(int ele : arr){
            int key = ele % m;
            hm.put(key, hm.getOrDefault(key, 0) + 1);
            if(hm.get(key) == k) possible = true;
        }
        if(!possible){
            System.out.println("No");
            System.exit(0);
        }
        System.out.println("Yes");
        for(HashMap.Entry<Integer, Integer> pair : hm.entrySet()){//at max 1 iteration of whole array
            if(pair.getValue() >= k){
                int rem = pair.getKey();
                int idx = 0;
                while(k > 0){//at max 1 iteration
                    if(arr[idx] % m == rem){
                        System.out.print(arr[idx]+" ");
                        k--;
                    }
                    idx++;
                }
                break;
            }
        }
    }
}
//TC: O(N)