package DPBootcamp.Contests.CF994Div2;

import java.util.Scanner;

public class ProbA {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            boolean flag = true;
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
                if(arr[i] > 0) flag = false;
            }
            if(flag) System.out.println(0);
            else{
                int lastPos = -1, zero = -1;
                int ans = 1;
                for(int i = 0; i < n; i++){
                    if(arr[i] == 0) zero = i;
                    if(arr[i] > 0 && lastPos == -1) lastPos = i;
                    else if(arr[i] > 0 && zero > lastPos){
                        ans = 2;
                        break;
                    }
                }
                System.out.println(ans);
            }
        }
    }
}
