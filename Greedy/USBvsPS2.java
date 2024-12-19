//https://codeforces.com/contest/762/problem/B
package DPBootcamp.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class USBvsPS2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer> usbPrices = new ArrayList<>();
        ArrayList<Integer> ps2Prices = new ArrayList<>();
        for(int i = 1; i <= m; i++){
            int price = sc.nextInt();
            String type = sc.nextLine();
            if(type.equals(" USB")) //bifurcating based on type
                usbPrices.add(price);
            else
                ps2Prices.add(price);
        }
        Collections.sort(usbPrices); //sorting the prices
        Collections.sort(ps2Prices);
        int count = 0;
        long cost = 0;
        int p1 = 0, p2 = 0;
        int usbOnlyLimit = Math.min(a, usbPrices.size());//we can only pick min(demand, supply)
        int ps2OnlyLimit = Math.min(b, ps2Prices.size());
        count += usbOnlyLimit + ps2OnlyLimit;//count of mouses picked for "only USB" & "only PS/2"
        while(p1 < usbOnlyLimit){
            cost += usbPrices.get(p1); //adding cost
            p1++;
        }
        while(p2 < ps2OnlyLimit){
            cost += ps2Prices.get(p2);
            p2++;
        }
        for(int i = 1; i <= c; i++){//checking for 'c'(both)
            if(p1 >= usbPrices.size() && p2 >= ps2Prices.size()) break; //ran out of mouses
            count++;
            if(p1 < usbPrices.size() && p2 < ps2Prices.size()){//mouses of both types available
                if(usbPrices.get(p1) <= ps2Prices.get(p2)){//pick the cheaper one
                    cost += usbPrices.get(p1);
                    p1++;
                }else{
                    cost += ps2Prices.get(p2);
                    p2++;
                }
            }else if(p1 < usbPrices.size()){ //only USB type available
                cost += usbPrices.get(p1);
                p1++;
            }else{ //only PS/2 type available
                cost += ps2Prices.get(p2);
                p2++;
            }
        }
        System.out.println(count + " " + cost);
    }
}
//TC: O(M)