package DPBootcamp.Graphs;

import java.util.Arrays;
import java.util.Scanner;
//shortest path of every node to every other node
public class FloydWarshall {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[][] dist = new long[n + 1][n + 1];
        for(long[] row : dist) Arrays.fill(row, Long.MAX_VALUE);
        for(int i = 1; i <= n; i++) dist[i][i] = 0;
        for(int i = 1; i <= m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();
            dist[x][y] = w;
        }
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(dist[i][k] != Long.MAX_VALUE && dist[k][j] != Long.MAX_VALUE)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(dist[i][j] == Long.MAX_VALUE ? -1 : dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
//TC: O(N^3)