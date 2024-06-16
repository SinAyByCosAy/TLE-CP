package DPBootcamp.Templates;

public class ImpFunctions {

    //Random value generator from [a, b]
    int rand(int a, int b){
        return (int) ((Math.random() * (b - a)) + a);
    }

    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
