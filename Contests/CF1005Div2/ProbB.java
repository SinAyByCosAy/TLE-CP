package DPBootcamp.Contests.CF1005Div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ProbB {
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
        while(t-- > 0){
            int n = fr.nextInt();
            int[] arr = new int[n];
            HashSet<Integer> hs = new HashSet<>();
            HashSet<Integer> duplicates = new HashSet<>();
            int l = 0, r = n;
            int count = 1;
            for(int i = 0; i < n; i++){
                int ele = fr.nextInt();
                if(hs.contains(ele)){
                    duplicates.add(ele);
                    count++;
                }
                hs.add(ele);
                arr[i] = ele;
            }
            if(duplicates.isEmpty()) System.out.println(1+" "+n);
            else if(count == n) System.out.println(0);
            else{
                int start = 1, end = n - 1;
                while(start <= end){
                    int mid = (start + end) / 2;
                    Idx res = check(arr, duplicates, mid);
                    if(res.l != -1 && res.r != -1){
                        l = res.l;
                        r = res.r;
                        start = mid + 1;
                    }else end = mid - 1;
                }
                System.out.println(l + " " + r);
            }
        }
    }
    public static Idx check(int[] arr, HashSet<Integer> duplicates, int target){
        int size = 0;
        int n = arr.length;
        boolean found = false;
        int l = -1, r = -1;
        for(int i = 0; i < n; i++){
            if(duplicates.contains(arr[i])){
                size = 0;
                found = false;
            }
            else{
                if(!found) {
                    l = i + 1;
                    found = true;
                }
                size++;
                if(size == target){
                    r = i + 1;
                    break;
                }
            }
        }
        return new Idx(l, r);
    }
}
class Idx{
    int l, r;
    Idx(int l, int r){
        this.l = l;
        this.r = r;
    }
}