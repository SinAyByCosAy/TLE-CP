//ICPC Amritapuri regional 2021
//given N strings of size M, all on server A. Move them one by one to server B.
//For moving, first string has cost : M for M bits
//after that, we incur a cost of min. #bits different from any of the strings sent earlier.
//Find min. cost

//Make graph of hamming distances between each string, then find MST.
package DPBootcamp.Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OptimalDataTransfer {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String[] data = new String[n];
        for(int i = 0; i < n; i++) data[i] = sc.nextLine();

        List<Edges> edges = new ArrayList<>();
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                edges.add(new Edges(i, j, getHammingDistance(data[i], data[j], m)));

        Collections.sort(edges, (a, b) -> Integer.compare(a.w, b.w));
        DSU3.init(n);
        int cost = m;
        for(int i = 0; i < edges.size(); i++){
            int u = edges.get(i).u, v = edges.get(i).v, w = edges.get(i).w;
            if(!DSU3.isSameSet(u, v)){
                DSU3.unionSet(u, v);
                cost += w;
            }
        }
        System.out.println(cost);
    }
    public static int getHammingDistance(String s1, String s2, int m){
        int count = 0;
        for(int i = 0; i < m; i++) if(s1.charAt(i) != s2.charAt(i)) count++;
        return count;
    }
}
