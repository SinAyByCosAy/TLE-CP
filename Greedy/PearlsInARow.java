//https://codeforces.com/problemset/problem/620/C
package DPBootcamp.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class PearlsInARow {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
    public static void main(String[] args){
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = fr.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = fr.nextInt();

        HashSet<Integer> hs = new HashSet<>();
        ArrayList<Segment> result = new ArrayList<>();
        int l = 0;//start of segment
        for(int i = 0; i < n; i++){
            int ele = arr[i];
            if(hs.contains(ele)){
                result.add(new Segment(l + 1, i + 1)); //1 based indexing
                l = i + 1;
                hs.clear(); //ready for new segment
            }else if(i == n - 1){//didn't find a duplicate in the current segment till the end
                if(result.size() != 0) result.get(result.size() - 1).r = n; //modify previous segment's right end
            }else hs.add(ele);
        }
        if(result.size() == 0) out.println(-1);
        else{
            out.println(result.size());
            for(Segment pair : result){
                out.println(pair.l + " " + pair.r);
            }
        }
        out.flush();
    }
}
class Segment{
    int l, r;
    Segment(int l, int r){
        this.l = l;
        this.r = r;
    }
}
//TC: O(N)