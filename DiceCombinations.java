package DPBootcamp;

public class DiceCombinations {
    public static void main(String args[]){
        int n = 7;
        System.out.println("Number of ways to get sum: "+n+" with dice rolls is: "+ways(n));
    }
    static int ways(int n){
        if(n==0||n==1)
            return 1;
        int count = 0;
        for(int i=1;i<=6 & n-i>=0;i++){
            count += ways(n-i);
        }
        return count;
    }
}
