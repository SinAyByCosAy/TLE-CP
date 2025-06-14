package DPBootcamp.Graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//given a matrix of oranges and a cell that has a rotten orange. Every second the rotten orange infests it's neighbors in 4 directions.
//find the time it takes to make all the oranges rotten.
public class RottenOrange {
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Pair rotOrange = new Pair(sc.nextInt(), sc.nextInt());
        Queue<Pair> queue = new LinkedList<>();//keep track of the BFS order
        boolean[][] visited = new boolean[n][n];
        queue.add(rotOrange);//process the root node(rotten orange)
        visited[rotOrange.x][rotOrange.y] = true;
        int[][] time = new int[n][m];
        int ans = 0;
        while(!queue.isEmpty()){
            Pair topNode = queue.remove();//remove the top node, time to add it's neighbors
            for(int[] dx : dir){//move in all 4 directions
                int i = topNode.x + dx[0];
                int j = topNode.y + dx[1];
                if(i >=0 && j >= 0 && i < n && j < m && !visited[i][j]){//valid node
                    queue.add(new Pair(i, j));//process the valid node
                    visited[i][j] = true;
                    time[i][j] = time[topNode.x][topNode.y] + 1;//time taken is previous node's time + 1
                    ans = Math.max(ans, time[i][j]);
                }
            }
        }
        System.out.println(ans);
    }
}
class Pair{
    int x, y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}