//https://codeforces.com/problemset/problem/154/B
package DPBootcamp.NumberTheory;

import java.util.ArrayList;
import java.util.Scanner;

//only values with unique prime factors can be co-prime and can be turned on together
//we can keep track of the prime factors already in use and for which number. We don't allow a collider with any duplicate prime factor
//SPF array to find prime factors
public class Colliders {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] flag = new boolean[n + 1]; //flag array that tells us if a collider is already on or not
        int[] spf = getSPFArray((int) 1e6); //O(Nlog(logN))
        int[] colliderMap = new int[n + 1];//factors map of colliders
        while(m-- > 0){//O(M)
            char op = sc.next().charAt(0);
            int val = sc.nextInt();
            if(op == '+')
                turnOn(val, spf, flag, colliderMap);
            else
                turnOff(val, spf, flag, colliderMap);
        }
    }
    public static void turnOn(int val, int[] spf, boolean[] flag, int[] colliderMap){
        if(flag[val]){
            System.out.println("Already on");
            return;
        }
        ArrayList<Integer> factorsList = getPrimeFactors(val, spf);//O(logN)
        for(int factor : factorsList){//checking for collision, O(logN)
            if(colliderMap[factor] != 0){
                System.out.println("Conflict with " + colliderMap[factor]);
                return;
            }
        }
        flag[val] = true;//activating collider
        for(int factor : factorsList)//setting factors map, O(logN)
            colliderMap[factor] = val;
        System.out.println("Success");
    }
    public static void turnOff(int val, int[] spf, boolean[] flag, int[] colliderMap){
        if(!flag[val]){
            System.out.println("Already off");
            return;
        }
        flag[val] = false;//deactivating collider
        ArrayList<Integer> factorsList = getPrimeFactors(val, spf);//O(logN)
        for(int factor : factorsList)//removing collider's factors, O(logN)
            colliderMap[factor] = 0;
        System.out.println("Success");
    }
    public static ArrayList<Integer> getPrimeFactors(int val, int[] spf){
        ArrayList<Integer> factorsList = new ArrayList<>();
        while(val > 1){
            int factor = spf[val];
            while(val % factor == 0)
                val /= factor;
            factorsList.add(factor);
        }
        return factorsList;
    }
    public static int[] getSPFArray(int limit){
        int[] spf = new int[limit + 1];
        for(int i = 1; i <= limit; i++)
            spf[i] = i;

        for(int i = 2; i <= (int) 1e4; i++){
            if(spf[i] == i){
                for(int j = i * i; j <= limit; j += i)
                    spf[j] = Math.min(spf[j], i);
            }
        }
        return spf;
    }
}
//TC: O(N + M * logN)