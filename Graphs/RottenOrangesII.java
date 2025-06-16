package DPBootcamp.Graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//Now we have multiple rotten oranges at the start, all start to infest their neighbors.
//Find the time it'll take to rot all oranges in the matrix.
public class RottenOrangesII {
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt(); //number of queries
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for(int i = 1; i <= q; i++){//only change needed is to add all the rotten nodes first to the queue
            int row = sc.nextInt();
            int col = sc.nextInt();
            queue.add(new Pair(row, col));
            visited[row][col] = true;
        }
        int[][] dist = new int[n][m];
        int ans = 0;
        while(!queue.isEmpty()){
            Pair topNode = queue.remove();
            for(int[] dx : dir){
                int i = topNode.x + dx[0];
                int j = topNode.y + dx[1];
                if(i >= 0 && i < n && j >= 0 && j < m && !visited[i][j]){
                    queue.add(new Pair(i, j));
                    visited[i][j] = true;
                    dist[i][j] = dist[topNode.x][topNode.y] + 1;
                    ans = Math.max(ans, dist[i][j]);
                }
            }
        }
        System.out.println(ans);
    }
}