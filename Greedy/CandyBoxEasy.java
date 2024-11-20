//https://codeforces.com/contest/1183/problem/D
package DPBootcamp.Greedy;

import java.util.*;

public class CandyBoxEasy {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while(q-- > 0){
            int n = sc.nextInt();
            int[] freq = new int[n + 1];
            for(int i = 0; i < n; i++){
                int ele = sc.nextInt();
                freq[ele]++; //freq of all a[i] types
            }
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 1; i <= n; i++)
                if(freq[i] > 0)
                    list.add(freq[i]); //adding all frequencies to a list
            Collections.sort(list); //sorting frequencies

            int last = list.get(list.size() - 1); //we'll start by picking the max frequency
            long ans = 0;
            for(int i = list.size() - 1; i >= 0; i--){
                if(last == 0) break;
                if(list.get(i) >= last){ //we found required no. of frequencies
                    ans += last;
                    last--;
                }else{//we found less no. of frequencies
                    ans += list.get(i);
                    last = list.get(i) - 1;
                }
            }
            System.out.println(ans);
        }
    }
}
//TC : O(NlogN)