//https://leetcode.com/problems/the-dining-philosophers/description/
//Multithreading problem
package DPBootcamp.Random;

import java.util.Arrays;

class DiningPhilosophers {

    private final Object lock = new Object();
    private final boolean[] forkFree = new boolean[5];

    public DiningPhilosophers() {
        Arrays.fill(forkFree, true);
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int leftFork = philosopher;
        int rightFork = (philosopher + 1) % 5;

        synchronized(lock){
            while(!forkFree[leftFork] || !forkFree[rightFork]){
                lock.wait();
            }
            forkFree[leftFork] = false;
            forkFree[rightFork] = false;
        }

        try{
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
        }finally{
            synchronized(lock){
                forkFree[leftFork] = true;
                forkFree[rightFork] = true;
                lock.notifyAll();
            }
        }
    }
}