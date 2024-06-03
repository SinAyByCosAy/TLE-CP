//https://codeforces.com/problemset/problem/1425/F
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class FlamingoesOfMystery {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        int n = sc.nextInt();
        int[] arr = new int[n];

        int q = query(1, 2);
        arr[2] = query(1, 3) - q;
        arr[1] = query(2, 3) - arr[2];
        arr[0] = q - arr[1];
        for(int i=3; i<n; i++){
            q = query(i, i+1);
            arr[i] = q - arr[i-1];
        }

        answer(arr);
    }
    public static int query(int x, int y){
        System.out.println("? "+x+" "+y);
        return sc.nextInt();
    }
    public static void answer(int[] arr){
        System.out.print("! ");
        for(int ele : arr){
            System.out.print(ele + " ");
        }
    }
}
