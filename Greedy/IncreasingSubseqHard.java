//https://codeforces.com/problemset/problem/1157/C2
package DPBootcamp.Greedy;

import java.util.Scanner;

public class IncreasingSubseqHard {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        int p1 = 0, p2 = n - 1;
        int lastPicked = Integer.MIN_VALUE;
        StringBuilder ans = new StringBuilder();
        while(p1 <= p2){
            if(arr[p1] <= lastPicked && arr[p2] <= lastPicked) break; //no valid elements
            //we have an element to pick
            if(arr[p1] > lastPicked && arr[p2] > lastPicked){//need to choose b/w L & R
                if(arr[p1] < arr[p2]){//pick L
                    lastPicked = arr[p1];
                    p1++;
                    ans.append('L');
                }else if(arr[p2] < arr[p1]){//pick R
                    lastPicked = arr[p2];
                    p2--;
                    ans.append('R');
                }else{//both elements are same, need to compare suffix vs prefix and pick a side
                    int prefixCount = 0, suffixCount = 0;
                    for(int i = p1 + 1; i < p2; i++){
                        if(arr[i] <= arr[i - 1]) break; //seq. not increasing anymore
                        prefixCount++;
                    }
                    for(int i = p2 - 1; i > p1; i--){
                        if(arr[i] <= arr[i + 1]) break;
                        suffixCount++;
                    }
                    if(prefixCount >= suffixCount){
                        for(int i = 1; i<= prefixCount + 1; i++) ans.append('L'); //+1 to add the element that was same
                        break; //seq. ends here, no more options are valid
                    }else{
                        for(int i = 1; i<= suffixCount + 1; i++) ans.append('R');
                        break;
                    }
                }
            }
            else if(arr[p1] > lastPicked){ //only have 1 option to pick
                lastPicked = arr[p1];
                p1++;
                ans.append('L');
            }else{
                lastPicked = arr[p2];
                p2--;
                ans.append('R');
            }
        }
        System.out.println(ans.length());
        System.out.println(ans);
    }
}
//TC: O(N)