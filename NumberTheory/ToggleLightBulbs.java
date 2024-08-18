package DPBootcamp.NumberTheory;

import java.util.Scanner;

//given n bulbs, each bulb can be in one of the two states: 0 -> off, 1 -> on
//initially all bulbs are off(0)
//operation on every bulb possible: for every i-th bulb, toggle all bulbs placed at multiples of 'i'
//figure out after all these operations, how many light bulbs are on(1) ?
public class ToggleLightBulbs {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //perfect squares have odd factors, hence they will remain 1
        //#perfect sqs = Floor[ sqrt(N) ]
        System.out.println((int)Math.sqrt(n));
    }
}