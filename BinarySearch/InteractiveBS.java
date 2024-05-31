package DPBootcamp.BinarySearch;

import java.util.Scanner;

import static java.lang.System.exit;


class Interactor{//mimicking online judge
    int max_queries = 10;//limit on queries we can have
    int hidden_answer, query_count;
    int rand(int a, int b){
        return (int) ((Math.random() * (b - a)) + a);
    }
    void init(){//values that need to be initialized for every run of testcase
        hidden_answer = rand(1, 100);//generating random testcases
        query_count = 0;//counter for checking #queries made in a single testcase
    }
    char make_query(int x){//online judge interaction with our queries
        if(++query_count >  max_queries){
            System.out.println("max query limit reached "+max_queries);
            exit(1);
        }
        char response;
        if(x == hidden_answer) response = '=';
        else if(x > hidden_answer) response = '>';
        else response = '<';
        System.out.println(response);
        return response;
    }
    void check_answer(int x){//online judge checking state of final answer submitted
        if(x == hidden_answer) System.out.println("Passed for "+ hidden_answer);
        else {
            System.out.println("Failed for "+hidden_answer);
            exit(1);
        }
    }
}
public class InteractiveBS {//Logic code
    static boolean LOCAL = true;//IMPORTANT : change to false when submitting to online judge
    static int testcases = LOCAL ? 100 : 1;
    static Interactor obj = new Interactor();
    public static char checker(int x){
        Scanner sc = new Scanner(System.in);
        System.out.println("?"+x);
        if(LOCAL) return obj.make_query(x); //reading response from local online judge
        return sc.next().charAt(0); //reading user input
    }
    public static void answer(int x){
        System.out.println("!"+x);
        if(LOCAL) obj.check_answer(x); //sending final answer to local online judge
    }
    public static void main(String[] args){
        while(testcases-- > 0) {
            if(LOCAL) obj.init();
            int start = 1, end = 100, ans = -1;
            while (start <= end) {
                int mid = (start + end) / 2;
                char ch = checker(mid);
                if (ch == '=') {
                    ans = mid;
                    break;
                } else if (ch == '>') {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            answer(ans);
        }
    }
}
