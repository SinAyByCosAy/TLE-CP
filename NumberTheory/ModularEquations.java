//https://codeforces.com/contest/495/problem/B
package DPBootcamp.NumberTheory;

import java.util.Scanner;

public class ModularEquations {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        if(a == b){
            System.out.println("infinity");
            System.exit(0);
        }
        int target = a - b; //key observation
        int count = 0;
        for(int i = 1; i * i <= target; i++){
            if(target % i == 0) {
                if(i > b) count++; //another key observation
                if(target / i != i && target / i > b)
                    count ++;
            }
        }
        System.out.println(count);
    }
}