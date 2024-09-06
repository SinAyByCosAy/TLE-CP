package DPBootcamp.Templates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CSESTemplate {
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

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        // Input examples
        int n = fr.nextInt();  // Single integer
        long m = fr.nextLong();  // Single long
        double d = fr.nextDouble();  // Single double
        String s = fr.next();  // Single word
        String line = fr.nextLine();  // Whole line
        char c = fr.next().charAt(0);  // Single character

        // Read array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fr.nextInt();
        }

        // Read 2D array
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = fr.nextInt();
            }
        }

        // Output examples
        out.println(n);  // Print with newline
        out.print(m + " ");  // Print without newline
        out.printf("%.6f\n", d);  // Print formatted double

        // Print array
        for (int x : arr) {
            out.print(x + " ");
        }
        out.println();

        // Always flush the output
        out.flush();
    }
}
