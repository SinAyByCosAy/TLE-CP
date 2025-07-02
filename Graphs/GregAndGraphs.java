//https://codeforces.com/problemset/problem/295/B
package DPBootcamp.Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GregAndGraphs {
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
        long[][] grid = new long[n + 1][n + 1];
        for(int i = 1; i <= n; i++) for(int j = 1; j <= n; j++) grid[i][j] = fr.nextInt();
        int[] removalNodes = new int[n];
        for(int i = 0; i < n; i++) removalNodes[i] = fr.nextInt();

        boolean[] visited = new boolean[n + 1];
        long[] ans = new long[n];
        for(int rem = n - 1; rem >= 0; rem--){//going from the back
            int k = removalNodes[rem]; //k value to introduce
            visited[k] = true; //marked
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    grid[i][j] = Math.min(grid[i][j], grid[i][k] + grid[k][j]);
                    if(visited[i] && visited[j]) ans[rem] += grid[i][j]; //both nodes are valid for the current scenario
                }
            }
        }
        for(int i = 0; i < n; i++) out.print(ans[i] + " ");
        out.flush();
    }
}
//TC: O(N^3)