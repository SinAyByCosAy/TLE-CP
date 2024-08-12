package DPBootcamp.TwoPointers;

import java.util.*;

public class IraAndFlamenco {
    static int mod = 1000 * 1000 * 1000 + 7;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] arr = new int[n + 1];
            for(int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            arr[n] = Integer.MAX_VALUE; //extra value for list creation
            Arrays.sort(arr);
            ArrayList<Pair> list = new ArrayList<>();
            int count = 0;
            for(int i = 0; i < n; i++){
                count++;
                if(arr[i] != arr[i + 1]) {
                    list.add(new Pair(arr[i], count)); //value and its count
                    count = 0;
                }
            }
            long ans = 0;
            int prod = 1;
            //sliding window
            for(int i = 0; i < list.size(); i++){
                prod = (int)((prod * 1l * list.get(i).count) % mod); //including contribution of new element

                if(i >= m){
                    prod = divide(prod, list.get(i - m).count); //removing contribution of outgoing element
                }

                if(i >= m - 1){
                    if(list.get(i).val - list.get(i - m + 1).val < m){ //if valid
                        ans = (ans + prod) % mod; //adding to answer
                    }
                }
            }
            System.out.println(ans);
        }
    }

    // Modulo division functions
    public static int mul(int x, int y){
        return (int)((x * 1l * y) % mod);
    }
    public static int power(int x, int y){
        int ans = 1;
        while(y > 0){
            if((y & 1) == 1) ans = mul(ans, x);
            x = mul(x, x);
            y = y >> 1;
        }
        return ans;
    }
    public static int divide(int x, int y){
        return mul(x, power(y, (mod - 2)));
    }
    static class Pair{
        int val, count;
        Pair(int val, int count){
            this.val = val;
            this.count = count;
        }
    }
}