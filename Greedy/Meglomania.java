//https://atcoder.jp/contests/abc131/tasks/abc131_d
package DPBootcamp.Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Meglomania {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        JobDetails[] job = new JobDetails[n];
        for(int i = 0; i < n; i++)
            job[i] = new JobDetails(sc.nextInt(), sc.nextInt());
        Arrays.sort(job, (a, b) -> Integer.compare(a.deadline, b.deadline));
        long lastTime = 0; //keeps track of when last job finished
        boolean flag = true;
        for(int i = 0; i < n; i++){
            if(lastTime + job[i].completeTime <= job[i].deadline) //we can do the current job
                lastTime += job[i].completeTime;
            else{//can't do the current job
                flag = false;
                break;
            }
        }
        if(flag) System.out.println("Yes");
        else System.out.println("No");
    }
}
class JobDetails{
    int completeTime, deadline;
    JobDetails(int completeTime, int deadline){
        this.completeTime = completeTime;
        this.deadline = deadline;
    }
}
//TC: O(NlogN)