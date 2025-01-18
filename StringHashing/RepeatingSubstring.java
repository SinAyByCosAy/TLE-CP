//https://cses.fi/problemset/task/2106/
package DPBootcamp.StringHashing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class RepeatingSubstring {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    public static void main(String[] args){
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        String s = fr.nextLine();
        out.println(findRepeat(s));
        out.flush();
    }
    public static String findRepeat(String s){
        Hash a = new Hash(s);
        int n = s.length();
        int start = 1, end = n;
        int pos = -1, ans = 0;
        while(start <= end){//exploring lengths using BS
            int mid = (start + end) / 2;
            int idx = getRepeatForLength(a, n, mid); //end position of valid substring
            if(idx != -1){
                ans = mid; //length of valid substring
                pos = idx; //end index
                start = mid + 1;
            }else end = mid - 1;
        }
        if(ans == 0) return "-1";
        return s.substring(pos - (ans - 1), pos + 1);
    }
    public static int getRepeatForLength(Hash a, int n, int size){
        HashSet<String> hs = new HashSet<>();
        for(int i = size - 1; i < n; i++){
            Hashes subHash = a.get(i - (size - 1), i);
            String sub = subHash.hash1 + "," + subHash.hash2;
            if(hs.contains(sub)) return i;
            hs.add(sub);
        }
        return -1;
    }
}
//TC: O(NlogN)