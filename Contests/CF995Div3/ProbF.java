//https://codeforces.com/contest/2051/problem/F
package DPBootcamp.Contests.CF995Div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class ProbF {
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
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
        int t = fr.nextInt();
        while(t-- > 0) {
            int n = fr.nextInt();
            int m = fr.nextInt();
            int q = fr.nextInt();
            ArrayList<Pair> ranges = new ArrayList<>();
            ranges.add(new Pair(m, m));
            for(int i = 1; i <= q; i++){
                ranges = applyOP(fr.nextInt(), n, ranges);
                out.print(getRangeSizes(ranges) + " ");
            }
            out.println();
        }
        out.flush();
    }
    public static ArrayList<Pair> applyOP(int x, int n, ArrayList<Pair> ranges){
        ArrayList<Pair> newRanges = new ArrayList<>();
        for(Pair range : ranges){
            if(x < range.l) newRanges.add(new Pair(range.l - 1, range.r));
            else if(x > range.r) newRanges.add(new Pair(range.l, range.r + 1));
            else{
                newRanges.add(new Pair(1, 1));
                newRanges.add(new Pair(n, n));
                if(range.l < range.r) newRanges.add(new Pair(range.l, range.r));
            }
        }
        return mergeIntervals(newRanges);
    }
    public static ArrayList<Pair> mergeIntervals(ArrayList<Pair> ranges){
        Collections.sort(ranges, (a, b) -> {
            if(a.l != b.l) return Integer.compare(a.l, b.l);
            return Integer.compare(a.r, b.r);
        });
        ArrayList<Pair> mergedRanges = new ArrayList<>();
        int prevL = ranges.get(0).l;
        int prevR = ranges.get(0).r;
        for(Pair range : ranges){
            if(range.l <= prevR) prevR = range.r;
            else{
                mergedRanges.add(new Pair(prevL, prevR));
                prevL = range.l;
                prevR = range.r;
            }
        }
        mergedRanges.add(new Pair(prevL, prevR));
        return mergedRanges;
    }
    public static int getRangeSizes(ArrayList<Pair> ranges){
        int size = 0;
        for(Pair range : ranges){
            size += range.r - range.l + 1;
        }
        return size;
    }
}
class Pair{
    int l, r;
    Pair(int l, int r){
        this.l = l;
        this.r = r;
    }
}