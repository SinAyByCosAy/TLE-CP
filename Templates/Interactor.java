package DPBootcamp.Templates;

import static java.lang.System.exit;

public class Interactor {
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
