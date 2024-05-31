//Hidden array of N integers. We need to find any index if the majority element or -1 if not present.
//We can ask for any index to judge and it'll tell us if that index contains a majority element or not.
//Majority element : Count of an element is > N/2
//Ex - [5 4 3 4 4 4 2]    (1-based indexing),
//?1 -> 0 (no)
//?3 -> 0
//?7 -> 0
//?2 -> 1 (yes)
//!2

package DPBootcamp.BinarySearch;

import java.util.Scanner;
public class InteractiveRandomized {
    static Scanner sc = new Scanner(System.in);
    static int rand(int a, int b){
        return (int) ((Math.random() * (b - a)) + a);
    }

    public static int checker(int x){
        System.out.println("?"+x);
        return sc.nextInt();
    }
    public static void answer(int x){
        System.out.println("!"+x);
    }
    public static void main(String[] args){
        int N = sc.nextInt();
        int limit = 20;
        for(int i=1;i<=limit;i++){
            int idx = rand(1, N);//picking indexes at random
            if(checker(idx) == 1){
                answer(idx);
                return;
            }
        }
        answer(-1);
    }
}
