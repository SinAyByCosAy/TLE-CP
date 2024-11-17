package DPBootcamp.Greedy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class DoubledArray {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] doubled = new int[n];
        for(int i = 0; i < n; i++)
            doubled[i] = sc.nextInt();
        System.out.println(Arrays.toString(findOriginalArray(doubled)));
    }
    public static int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if(n % 2 == 1) return new int[0];
        int[] result = new int[n / 2];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int ele : changed)
            map.put(ele, map.getOrDefault(ele, 0) + 1); //ordered map to get the smallest element quickly

        int index = 0;
        while(!map.isEmpty()){
            int original = map.firstKey();//smallest element will be part of the original array
            int doubled = original * 2;

            result[index++] = original;
            map.put(original, map.get(original) - 1); //remove original element : O(logN)
            if(map.get(original) == 0) map.remove(original);
            if(!map.containsKey(doubled)) return new int[0]; //if doubled element is not present, not a doubled array
            map.put(doubled, map.get(doubled) - 1); //remove doubled element : O(logN)
            if(map.get(doubled) == 0) map.remove(doubled);
        }
        return result;
    }
}
//TC : O(N * logN)