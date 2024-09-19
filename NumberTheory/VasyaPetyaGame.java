//https://codeforces.com/contest/576/problem/A
package DPBootcamp.NumberTheory;

import java.util.ArrayList;
import java.util.Scanner;

public class VasyaPetyaGame {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] spf = getSPFArray((int) 1e3);
        ArrayList<Integer> list = getUniqueQuestions(n, spf);
        System.out.println(list.size());//questions required
        for(int ele : list)
            System.out.print(ele+" ");
    }
    public static ArrayList<Integer> getUniqueQuestions(int n, int[] spf){
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] questionsCollected = new boolean[n + 1];
        for(int i = 2; i <= n; i++){//O(N)
            int x = i;
            while(x > 1){//O(logN)
                int factor = spf[x];
                int question = 1;
                while(x % factor == 0){
                    x /= factor;
                    question *= factor; //calculating power of prime factor
                }
                if(!questionsCollected[question]) //if pf is not asked before, we need to ask it
                    list.add(question);
                questionsCollected[question] = true; //pf needs to be marked(it can already be marked)
            }
        }
        return list;
    }
    public static int[] getSPFArray(int limit){
        int[] spf = new int[limit + 1];
        for(int i = 1; i <= limit; i++)
            spf[i] = i;
        for(int i = 2; i <= limit; i++){
            if(spf[i] == i){
                for(int j = i * i; j <= limit; j += i)
                    spf[j] = Math.min(spf[j], i);
            }
        }
        return spf;
    }
}
//TC: O(NlogN)