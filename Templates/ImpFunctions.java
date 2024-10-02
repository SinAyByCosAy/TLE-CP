package DPBootcamp.Templates;

import java.util.*;

public class ImpFunctions {

    //Random value generator from [a, b]
    int rand(int a, int b){
        return (int) ((Math.random() * (b - a)) + a);
    }

    //gcd
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    //comparator
    public static void sortList(List<Integer> arr){
        Collections.sort(arr, (a, b) -> Integer.compare(a, b));
    }

    public static void sortArray(Integer[] arr){
        Arrays.sort(arr, (a, b) -> Integer.compare(a, b)); //requires an object type
    }

    //hashmap iterator
    public static void hashMapIterator(HashMap<Integer, Integer> hm){
        for (Map.Entry<Integer, Integer> ele : hm.entrySet()) {
            System.out.println(ele.getKey() + " " + ele.getValue());
        }
    }


    //A^N, Binary Exponentiation
    public static long binaryExpo(int a, int n){
        long res = 1;
        while(n >  0){
            if((n & 1) == 1){
                res *= 1l * a; //res %= mod
            }
            a *= a; //a %= mod
            n >>= 1;
        }
        return res;
    }


    //Modulo division when mod value is prime
    //(x / y) % mod = [x * y ^ (mod - 2)] % mod
    static int mod = (int)1e9 + 7;
    public static int modMul(int x, int y, int m){
        return (int)((x * 1l * y) % m);
    }

    //multiplicative modulo inverse under a prime: (x ^ (m-2)) % m
    public static int mmInvPrime(int x, int m){
        int y = m - 2;
        int ans = 1;
        while(y > 0){
            if((y & 1) == 1) ans = modMul(ans, x, m);
            x = modMul(x, x, m);
            y = y >> 1;
        }
        return ans;
    }
    public static int divide(int x, int y){
        return modMul(x, mmInvPrime(y, mod), mod);
    }


    //Binomial Coefficient under mod
    public static int modNCR(int n, int r, int mod){
        int[] factorial = getFactorial((int)1e6, mod); //pre-computed factorials
        int[] inverseFactorial = getInverseFactorial((int)1e6, factorial, mod); //pre-computed inverse factorials
        return modMul(modMul(factorial[n], inverseFactorial[r], mod), inverseFactorial[n - r], mod);
    }
    public static int[] getFactorial(int limit, int mod){
        int[] factorial = new int[limit + 1];
        factorial[0] = 1;
        for(int i = 1; i <= limit; i++){ //O(N)
            factorial[i] = modMul(factorial[i - 1], i, mod);
        }
        return factorial;
    }
    public static int[] getInverseFactorial(int limit, int[] factorial, int mod){
        int[] inverseFactorial = new int[limit + 1];
        inverseFactorial[limit] = mmInvPrime(factorial[limit], mod); //O(log m)
        for(int i = limit - 1; i >= 0; i--){ //O(N)
            inverseFactorial[i] = modMul(inverseFactorial[i + 1], i + 1, mod);
        }
        return inverseFactorial;
    }

    //Binomial coefficient w/o mod, nCr
    //send nCr or nC(n-r) based on whichever is smaller
    public static int nCr(int n, int r){
        long ans = 1;
        for(int i = 0; i < r; i++){
            ans *= (n - i); //numerator : n * (n - 1) * (n - 2) .... (n - r + 1)
            ans /= (i + 1); //denominator : 1 * 2 * 3 .... r
        }
        return (int) ans;
    }


    //Euler totient value
    public static int phi(int n){//can be also done using SPF
        //TC: O(sqrt(N))
        int result = n;
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                result -= result / i;
                while(n % i == 0)
                    n /= i;
            }
        }
        if(n > 1)
            result -= result / n;

        return result;
    }

    public static int[] phiOneToN(int n){
        //similar to sieve or SPF construction logic, TC: O(N * log(logN)) ~ O(N)
        int[] phiValues = new int[n + 1];
        for(int i = 1; i <= n; i++)
            phiValues[i] = i;

        for(int i = 2; i <= n; i++){
            if(phiValues[i] == i){//found a prime
                for(int j = i; j <= n; j += i)//contribution to all the multiples of prime
                    phiValues[j] -= phiValues[j] / i;
            }
        }
        return phiValues;
    }
}
