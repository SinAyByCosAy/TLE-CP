package DPBootcamp.NumberTheory;

import java.util.Scanner;

public class EulerTotient {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long result = n;
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                result -= result / i;
                while(n % i == 0)
                    n /= i;
            }
        }
        if(n > 1)
            result -= result / n;
        System.out.println(result);
    }
}
//TC: O(sqrt(N))