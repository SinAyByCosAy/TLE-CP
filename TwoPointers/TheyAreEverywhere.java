package DPBootcamp.TwoPointers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class TheyAreEverywhere {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        HashSet<Character> hs = new HashSet<>();
        for(int i = 0; i < n; i++)
            hs.add(s.charAt(i));
        int target = hs.size(); //# unique pokemons
        int ans = n;
        int i = 0, j = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        while (j < n) {
            char ch = s.charAt(j);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
            while(i <= j && hm.size() == target && hm.get(s.charAt(i)) > 1){
                //we are at a good segment and we can remove the extra element at the start of the window to make it smaller
                char x = s.charAt(i);
                hm.put(x, hm.get(x) - 1);
                i++;
            }
            if(hm.size() == target)//condition needed cuz initially segment would not have covered all unique elements
                ans = Math.min(ans, (j - i + 1));
            j++;
        }
        System.out.println("Two pointer approach: "+ans);
        System.out.println("Binary Search approach: "+searchMinFlats(s, target));
    }
    public static int searchMinFlats(String s, int target){
        int start = 1, end = s.length();
        int ans = end;
        while(start <= end){
            int mid = (start + end) / 2; //window size
            if(check(s, target, mid)){// current window is possible
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
    public static boolean check(String s, int target, int k){
        HashMap<Character, Integer> hm = new HashMap<>(); //frequency map
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i); //add new element to window
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
            if(i >= k){//remove out of window element
                char rem = s.charAt(i - k);
                hm.put(rem, hm.get(rem) - 1);
                if(hm.get(rem) == 0)
                    hm.remove(rem);
            }
            if(i >= k - 1)
                if(hm.size() == target)//if window covers all pokemons, window is possible
                    return true;
        }
        return false;
    }
}