package DPBootcamp.BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class AggressiveCows {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int c = sc.nextInt();
            int[] pos = new int[n];
            for(int i=0;i<n;i++){
                pos[i] = sc.nextInt();
            }
            Arrays.sort(pos);
            System.out.println(getDistance(pos, c));
        }
    }
    public static int getDistance(int[] pos, int c){
        int start = 1, end = (int)1e9;
        int ans = 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(placeCows(pos, c, mid)){
                ans = mid;
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return ans;
    }
    public static boolean placeCows(int[] pos, int c, int dist){
        int lastCow = 0; //placing first cow on 0th index
        int count = 1;
        for(int i=1;i<pos.length;i++){
            if(pos[i] - pos[lastCow] >= dist){
                lastCow = i; //found a position to place a cow
                count++;
            }
            if(count == c)
                break;
        }
        return count == c;
    }
}
