package DPBootcamp.Templates;

import java.util.*;

public class ImpFunctions {

    //Random value generator from [a, b]
    int rand(int a, int b){
        return (int) ((Math.random() * (b - a)) + a);
    }

    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void sortList(List<Integer> arr){
        Collections.sort(arr, (a, b) -> Integer.compare(a, b));
    }

    public static void sortArray(Integer[] arr){
        Arrays.sort(arr, (a, b) -> Integer.compare(a, b)); //requires an object type
    }

    public static void hashMapIterator(HashMap<Integer, Integer> hm){
        for (Map.Entry<Integer, Integer> ele : hm.entrySet()) {
            System.out.println(ele.getKey() + " " + ele.getValue());
        }
    }

    //Modulo division when mod value is prime
    //(x / y) % mod = [x * y ^ (mod - 2)] % mod
    static int mod = 1000 * 1000 * 1000 + 7;
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
}
