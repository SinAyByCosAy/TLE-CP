//https://cses.fi/problemset/task/1073
package DPBootcamp.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Towers {
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
        TreeMap<Integer, Integer> towers = new TreeMap<>();
        int towerCount = 0;
        for(int i = 0; i < n; i++){
            int size = arr[i];
            if(towers.higherKey(size) == null){//current cube is the largest and requires new tower
                towerCount++;
            }else{//we can add curr cube on existing cube
                int cubeAtBottom = towers.higherKey(size);
                towers.put(cubeAtBottom, towers.get(cubeAtBottom) - 1);//we remove the bottom cube
                if(towers.get(cubeAtBottom) == 0) towers.remove(cubeAtBottom);
            }
            towers.put(size, towers.getOrDefault(size, 0) + 1);//we add the curr cube
        }
        out.println(towerCount);
        out.flush();
    }
}
//TC: O(NlogN)