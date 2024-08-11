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
}
