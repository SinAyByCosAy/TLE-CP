//https://codeforces.com/problemset/problem/702/C
package DPBootcamp.BinarySearch;

import java.util.Scanner;

public class CellularNetwork {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] cities = new int[n];
        int[] towers = new int[m];
        for(int i=0;i<n;i++){
            cities[i] = sc.nextInt();
        }
        for(int i=0;i<m;i++){
            towers[i] = sc.nextInt();
        }
        int minPossibleDistance = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            minPossibleDistance = Math.max(minPossibleDistance, getDistance(towers, cities[i]));
        }
        System.out.println(minPossibleDistance);
        System.out.println(binarySearchAnswer(cities, towers));
    }

    //Approach 1: For each city, find it's position among towers using Binary Search
    public static int getDistance(int[] towers, int city){ //find location of city in towers list
        int start = 0, end = towers.length - 1;
        int pos = towers.length;
        while(start <= end){
            int mid = (start + end) / 2;
            if(towers[mid] >= city){
                pos = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        int distance = Integer.MAX_VALUE;
        if(pos != 0)
            distance = Math.min(distance, city - towers[pos-1]);
        if(pos != towers.length)
            distance = Math.min(distance, towers[pos] - city);
        return distance;
    }//TC:O(N * log M)

    //Approach 2: Binary Search on answer that is possible distance
    public static long binarySearchAnswer(int[] cities, int[] towers){
        int n = cities.length, m = towers.length;
        long start = 0, end = (long)2e9;
        long ans = end;
        while(start <= end){
            long mid = (start + end) / 2; //test distance
            int i=0, j=0;
            while(i<n && j<m){
                if(Math.abs(cities[i] - towers[j]) <= mid){//city can get connected to current tower with the specified distance
                    i++;
                }else{//trying the next tower
                    j++;
                }
            }
            if(i == n){//all cities got connection
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }//TC:O(N * log 2e9) or //M = max(tower pos)-min(city pos) or max(city pos)-min(tower pos) TC:O(N * log M)
}
