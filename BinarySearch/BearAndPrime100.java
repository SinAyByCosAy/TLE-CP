//https://codeforces.com/problemset/problem/679/A
package DPBootcamp.BinarySearch;

import java.util.Scanner;

//Composite no can be represented as a product of at least two prime factors using prime factorization.
//x = p1 * p2
//Don't need to check for values greater than 50
//if a number has 2 prime factors, it's composite
//edge case: square of primes : 9 (3 * 3). To handle this we also include sq of primes < 50   (4, 9, 25, 49)
public class BearAndPrime100 {
    static int rand(int a, int b){
        return (int) ((Math.random() * (b - a)) + a);
    }
    public static void main(String[] args){
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 4, 9, 25, 49};
        int divCount = 0;
        for(int ele : prime){
            String ans = checker(ele);
            if(ans.equals("yes")){
                divCount++;
            }
            if(divCount == 2)
                break;
        }
        if(divCount == 2)
            System.out.println("composite");
        else
            System.out.println("prime");
    }
    static String checker(int x){
        Scanner sc = new Scanner(System.in);
        System.out.println(x);
        return sc.nextLine();
    }
}
