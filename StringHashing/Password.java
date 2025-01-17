//https://codeforces.com/problemset/problem/126/B
package DPBootcamp.StringHashing;

import java.util.ArrayList;
import java.util.Scanner;

public class Password {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        Hash a = new Hash(s);
        ArrayList<Integer> lengths = new ArrayList<>();
        for(int i = 0; i < n; i++){//To get matching prefix and suffix lengths as candidates
            Hashes pre = a.get(0, i);
            Hashes suf = a.get(n - 1 - i, n - 1);
            if(pre.hash1 == suf.hash1 && pre.hash2 == suf.hash2) lengths.add(i + 1);
        }
        int len = getPassword(n, a, lengths);
        if(len == 0) System.out.println("Just a legend");
        else System.out.println(s.substring(0, len));
    }
    public static int getPassword(int n, Hash a, ArrayList<Integer> lengths){//apply BS on lengths
        int start = 0, end = lengths.size() - 1;
        int ans = 0;
        while(start <= end){
            int mid = (start + end) / 2;
            if(possiblePwd(a, n, lengths.get(mid))){//checking if current length has a password possible
                ans = lengths.get(mid);
                start = mid + 1;
            }else end = mid - 1;
        }
        return ans;
    }
    public static boolean possiblePwd(Hash a, int n, int size){
        Hashes pre = a.get(0, size - 1);
        for(int i = size; i < n - 1; i++){//checking excluding boundary elements
            Hashes check = a.get(i - (size - 1), i);
            if(pre.hash1 == check.hash1 && pre.hash2 == check.hash2) return true;
        }
        return false;
    }
}
//TC: O(NlogN)