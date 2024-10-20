package DPBootcamp.Combinatorics;

import java.util.ArrayList;
import java.util.Scanner;

public class DreamoonAndWifi {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int dest = 0;
        int n = s1.length();
        for(int i = 0; i < n; i++){
            if(s1.charAt(i) == '+')
                dest += 1;
            else
                dest -= 1;
        }
        ArrayList<Integer> points = new ArrayList<>();
        points.add(0);
        int l = 0, r = 0;
        for(int i = 0; i < n; i++){
            char ch = s2.charAt(i);
            if(ch == '+'){
                for(int j = l; j <= r; j++)
                    points.set(j, points.get(j) + 1);
            }else if(ch == '-'){
                for(int j = l; j <= r; j++)
                    points.set(j, points.get(j) - 1);
            }else{
                for(int j = l; j <= r; j++){
                    points.add(points.get(j) + 1);
                    points.add(points.get(j) - 1);
                }
                l = r + 1;
                r = points.size() - 1;
            }
        }
        double total = r - l + 1;
        int count = 0;
        for(int i = l; i <= r; i++){
            if(points.get(i) == dest) count ++;
        }
        System.out.println(count / total);
    }
}