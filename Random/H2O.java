//https://leetcode.com/problems/building-h2o/
package DPBootcamp.Random;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.BrokenBarrierException;

class H2O {

    private final Semaphore hydrogen = new Semaphore(2);
    private final Semaphore oxygen = new Semaphore(1);

    private final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        // Open the permits for the next molecule
        hydrogen.release(2);
        oxygen.release();
    });

    private void awaitBarrier() throws InterruptedException {
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        hydrogen.acquire();

        // This thread has bonded with this molecule
        releaseHydrogen.run();

        // Wait until all 3 threads have bonded
        awaitBarrier();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        oxygen.acquire();

        // This thread has bonded with this molecule
        releaseOxygen.run();

        // Wait until all 3 threads have bonded
        awaitBarrier();
    }
}