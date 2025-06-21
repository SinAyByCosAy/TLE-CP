//https://icpc.global/worldfinals/problems/2022-ICPC-World-Finals/icpc2022.pdf
package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TurningRed {
    public static int sum;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int b = sc.nextInt();
        sc.nextLine();
        char[] s = sc.nextLine().toCharArray();
        List<Integer>[] lights = new ArrayList[l + 1]; //light : bulbs
        List<Integer>[] bulbs = new ArrayList[b + 1]; //bulb : lights
        for(int i = 1; i <= l; i++) lights[i] = new ArrayList<>();
        for(int i = 1; i <= b; i++) bulbs[i] = new ArrayList<>();
        for(int i = 1; i <= b; i++){
            int k = sc.nextInt();
            for(int j = 1; j <= k; j++){//input and making graph
                int light = sc.nextInt();
                lights[light].add(i);
                bulbs[i].add(light);
            }
        }
        for(int i = 1; i <= l; i++){//if there is a light not connected to a bulb and it's not Red, we can never have a solution
            if(lights[i].size() == 0 && s[i - 1] != 'R'){
                System.out.println("impossible");
                return;
            }
        }
        boolean[] visitedBulbs = new boolean[b + 1]; //we need this to traverse on different components of bulbs
        int ans = 0;
        boolean isPossible = false;
        for(int i = 1; i <= b; i++){
            if(!visitedBulbs[i]){ //start of unvisited component
                isPossible = false;
                int minVal = Integer.MAX_VALUE;
                for(int startVal = 0; startVal <= 2; startVal++){//this value has to be applied only on the first node, rest values automatically get fixed
                    int[] bulbValue = new int[b + 1];
                    sum = 0;
                    Arrays.fill(bulbValue, -1);
                    if(dfs(lights, bulbs, bulbValue, visitedBulbs, i, startVal, s)){
                        //it's possible to place values in current opponent
                        isPossible = true;
                        minVal = Math.min(minVal, sum); //take min answer of all variations of current component
                    }
                }
                if(!isPossible) break; //no arrangement is possible on current component
                else ans += minVal;
            }
        }
        if(!isPossible) System.out.println("impossible");
        else System.out.println(ans);
    }
    public static boolean dfs(List<Integer>[] lights, List<Integer>[] bulbs, int[] bulbValue, boolean[] visitedBulbs, int currBulb, int val, char[] s){
        bulbValue[currBulb] = val;//setting value in current bulb
        sum += val;
        visitedBulbs[currBulb] = true;
        for(int light : bulbs[currBulb]){ //checking all connected lights(to current bulb)
            if(lights[light].size() == 1)//only one bulb connected to light
                if(val != 0 && s[light - 1] == 'R' || val != 1 && s[light - 1] == 'B' || val != 2 && s[light - 1] == 'G') return false;
            else{//two bulbs connected to light
                int b1 = lights[light].get(0);
                int b2 = lights[light].get(1);
                if(bulbValue[b1] == -1){ int t = b1; b1 = b2; b2 = t; } //b2 is now negative, if we had a negative
                int x = bulbValue[b1];
                int y = bulbValue[b2];
                if(x != -1 && y != -1)//both bulbs have been pressed already
                    if (s[light - 1] == 'R' && (x + y) % 3 != 0 || s[light - 1] == 'B' && (x + y) % 3 != 1 || s[light - 1] == 'G' && (x + y) % 3 != 2)
                        return false;
                else{//one bulb is negative(not pressed), b2
                    //this bulb has not been explored yet. We need to calculate it's value based on the light and b1's value(x)
                        // then move there
                    if(s[light - 1] == 'R' && x == 0 || s[light - 1] == 'B' && x == 1 || s[light - 1] == 'G' && x == 2) y = 0;
                    if(s[light - 1] == 'R' && x == 2 || s[light - 1] == 'B' && x == 0 || s[light - 1] == 'G' && x == 1) y = 1;
                    if(s[light - 1] == 'R' && x == 1 || s[light - 1] == 'B' && x == 2 || s[light - 1] == 'G' && x == 0) y = 2;
                    if(!dfs(lights, bulbs, bulbValue, visitedBulbs, b2, y, s)) return false;
                }
            }
        }
        return true;
    }
}
//TC: O(L + B)