//https://codeforces.com/contest/1167/problem/B
package DPBootcamp.BinarySearch;

import java.util.HashMap;
import java.util.Scanner;
public class LostNumbers {
    public static void main(String[] args){
        int[] nos = {4, 8, 15, 16, 23, 42};
        HashMap<Integer, Pair> mul = new HashMap<>();
        for(int i=0;i<6;i++){
            for(int j=i+1;j<6;j++){
                mul.put(nos[i] * nos[j], new Pair(nos[i], nos[j]));
            }
        }
        int[] result = new int[6];

        int res1 = checker(1,2);
        int res2 = checker(2,3);
        int res3 = checker(4,5);
        int res4 = checker(5,6);

        int l1, l2, r1, r2;
        //finding values for (1,2,3)
        l1 = mul.get(res1).x;
        l2 = mul.get(res1).y;
        r1 = mul.get(res2).x;
        r2 = mul.get(res2).y;
        if(l1 == r1 || l1 == r2){
            result[1] = l1;
        }else{
            result[1] = l2;
        }
        result[0] = res1 / result[1];
        result[2] = res2 / result[1];

        //finding values for (4,5,6)
        l1 = mul.get(res3).x;
        l2 = mul.get(res3).y;
        r1 = mul.get(res4).x;
        r2 = mul.get(res4).y;
        if(l1 == r1 || l1 == r2){
            result[4] = l1;
        }else{
            result[4] = l2;
        }
        result[3] = res3 / result[4];
        result[5] = res4 / result[4];

        answer(result);
    }
    static int checker(int i, int j){
        System.out.println("? "+i+" "+j);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    static void answer(int[] result){
        System.out.print("!");
        for(int i=0;i<6;i++){
            System.out.print(" "+result[i]);
        }
        System.out.println();
    }
}
class Pair{
    int x, y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}