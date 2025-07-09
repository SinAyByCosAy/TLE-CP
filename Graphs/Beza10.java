//https://codeforces.com/gym/102020/problem/B
package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;


public class Beza10 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        char[][] grid = new char[n][m];
        for(int i = 0; i < n; i++) grid[i] = sc.nextLine().toCharArray();
        List<Coord>[][][] moves = new ArrayList[n][m][3];

        int sx = 0, sy = 0, fx = n - 1, fy = m - 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                if(grid[i][j] != '#'){
                    moves[i][j][0] = assignKingMoves(i, j, grid);
                    moves[i][j][1] = assignQueenMoves(i, j, grid);
                    moves[i][j][2] = assignKnightMoves(i, j, grid);
                }
                if(grid[i][j] == 'B'){ sx = i; sy = j; }
                if(grid[i][j] == 'P'){ fx = i; fy = j; }
            }
        }
        boolean flag = false;
        TreeSet<MoveInfo> pq = new TreeSet<>();
        int[][][][] cost = new int[n][m][3][k + 1];
        for(int i = 0; i < n; i++) for(int j = 0; j < m; j++) for(int p = 0; p < 3; p++) for(int l = 0; l <= k; l++)
            cost[i][j][p][l] = Integer.MAX_VALUE;
        //Start making moves, 0:king, 1:queen, 2:knight
        cost[sx][sy][0][k] = 0;
        pq.add(new MoveInfo(sx, sy, 0, k, 0));
        while(!pq.isEmpty()){
            MoveInfo top = pq.pollFirst();
            //I am at this node, I can now move to other spots by either being the same piece or changing the piece if K allows.
            int x = top.x, y = top.y, piece = top.piece, remK = top.k, currCost = top.cost;
            if(x == fx && y == fy){ flag = true; System.out.println(currCost); break; }//we reached the exit node


            for (int newPiece = 0; newPiece <= 2; newPiece++) {
                if(remK == 0 && newPiece != piece) continue;//we can't make any changes to piece
                int diff = (newPiece == piece) ? 0 : 1;//if different piece, we can reduce k by 1
                List<Coord> nextPos = moves[x][y][newPiece];//get all the positions for the next move
                for(Coord pair : nextPos){
                    if(currCost + 1 < cost[pair.x][pair.y][newPiece][remK - diff]){
                        pq.remove(new MoveInfo(pair.x, pair.y, newPiece, remK - diff, cost[pair.x][pair.y][newPiece][remK - diff]));
                        pq.add(new MoveInfo(pair.x, pair.y, newPiece, remK - diff, currCost + 1));
                        cost[pair.x][pair.y][newPiece][remK - diff] = currCost + 1;
                    }
                }
            }
        }
        if(!flag) System.out.println(-1);
    }
    public static List<Coord> assignKingMoves(int x, int y, char[][] grid){
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; //row movement
        int[] dy = {0, 1, -1, 1, -1, -1, 0, 1}; //col movement
        List<Coord> pos = new ArrayList<>();
        for(int i = 0; i < dx.length; i++){
            int di = x + dx[i];
            int dj = y + dy[i];
            if(di >= 0 && di < grid.length && dj >= 0 && dj < grid[0].length && grid[di][dj] != '#')
                pos.add(new Coord(di, dj));
        }
        return pos;
    }
    public static List<Coord> assignQueenMoves(int x, int y, char[][] grid){
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; //row movement
        int[] dy = {0, 1, -1, 1, -1, -1, 0, 1}; //col movement
        List<Coord> pos = new ArrayList<>();
        for(int i = 0; i < dx.length; i++){
            int di = x + dx[i];
            int dj = y + dy[i];
            while(di >= 0 && di < grid.length && dj >= 0 && dj < grid[0].length && grid[di][dj] != '#'){
                pos.add(new Coord(di, dj));
                di += dx[i];
                dj += dy[i];
            }
        }
        return pos;
    }
    public static List<Coord> assignKnightMoves(int x, int y, char[][] grid){
        int[] dx = {-2, -2, 2, 2, -1, -1, 1, 1};
        int[] dy = {-1, 1, -1, 1, -2, 2, -2, 2};
        List<Coord> pos = new ArrayList<>();
        for(int i = 0; i < dx.length; i++){
            int di = x + dx[i];
            int dj = y + dy[i];
            if(di >= 0 && di < grid.length && dj >= 0 && dj < grid[0].length && grid[di][dj] != '#')
                pos.add(new Coord(di, dj));
        }
        return pos;
    }
}
class Coord{
    int x, y;
    Coord(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class MoveInfo implements Comparable<MoveInfo>{
    int x, y, piece, k, cost;
    MoveInfo(int x, int y, int piece, int k, int cost){
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.k = k;
        this.cost = cost;
    }
    public int compareTo(MoveInfo obj){
        if(this.cost != obj.cost) return Integer.compare(this.cost, obj.cost);
        if(this.x != obj.x) return Integer.compare(this.x, obj.x);
        if(this.y != obj.y) return Integer.compare(this.y, obj.y);
        if(this.k != obj.k) return Integer.compare(this.k, obj.k);
        return Integer.compare(this.piece, obj.piece);
    }
}
