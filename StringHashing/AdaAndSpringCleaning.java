//https://www.spoj.com/problems/ADACLEAN/
package DPBootcamp.StringHashing;

import java.util.HashSet;
import java.util.Scanner;

public class AdaAndSpringCleaning {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();

            Hash a = new Hash(s);
            HashSet<String> hs = new HashSet<>();
            for(int i = 0; i < n - (k - 1); i++){
                Hashes activityHash = a.get(i, i + (k - 1));
                hs.add(activityHash.hash1 + "," + activityHash.hash2); //hash1 and hash2 will be same for the first character of every string
            }
            System.out.println(hs.size());
        }
    }
}
//TC: O(N)