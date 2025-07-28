//https://codeforces.com/problemset/problem/510/C
package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FoxAndNames {
    static boolean flag = true;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] names = new String[n];
        for(int i = 0; i < n; i++) names[i] = sc.nextLine();
        List<Integer>[] adj = new ArrayList[26];
        for(int i = 0; i < 26; i++) adj[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            String s1 = names[i - 1];
            String s2 = names[i];
            boolean same = true;
            for(int j = 0; j < Math.min(s1.length(), s2.length()); j++){
                char ch1 = s1.charAt(j);
                char ch2 = s2.charAt(j);
                if(ch1 != ch2){//found diff chars, hence u -> v ordering
                    adj[ch1 - 'a'].add(ch2 - 'a');
                    same = false;
                    break;
                }
            }
            //invalid case
            if(same && s1.length() > s2.length()) { System.out.println("Impossible"); System.exit(0); }
        }
        boolean[] visited = new boolean[26];
        boolean[] inStack = new boolean[26];
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            if(!visited[i]) dfs(i, adj, visited, inStack, res);
        }
        Collections.reverse(res);
        if(!flag) System.out.println("Impossible");
        else
            for(int ele : res)
                System.out.print((char)(ele + 'a'));
    }
    public static void dfs(int node, List<Integer>[] adj, boolean[] visited, boolean[] inStack, List<Integer> res){
        inStack[node] = visited[node] = true;
        for(int neighbor : adj[node]){
            if(!visited[neighbor]) dfs(neighbor, adj, visited, inStack, res);
            else if(inStack[neighbor]) flag = false; //cycle existes
        }
        inStack[node] = false;
        res.add(node);
    }
}
//TC: O(N^2)