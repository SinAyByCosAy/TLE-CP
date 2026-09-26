//https://leetcode.com/problems/fizz-buzz-multithreaded/description/
package DPBootcamp.Random;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private final int n;
    private int counter = 1;

    private final Semaphore fizzSemaphore = new Semaphore(0);
    private final Semaphore buzzSemaphore = new Semaphore(0);
    private final Semaphore fizzBuzzSemaphore = new Semaphore(0);
    private final Semaphore numberSemaphore = new Semaphore(1);

    private volatile boolean done = false;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            fizzSemaphore.acquire();

            if (done) {
                return;
            }

            printFizz.run();
            counter++;

            signalNext();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            buzzSemaphore.acquire();

            if (done) {
                return;
            }

            printBuzz.run();
            counter++;

            signalNext();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            fizzBuzzSemaphore.acquire();

            if (done) {
                return;
            }

            printFizzBuzz.run();
            counter++;

            signalNext();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            numberSemaphore.acquire();

            if (done) {
                return;
            }

            printNumber.accept(counter);
            counter++;

            signalNext();
        }
    }

    private void signalNext() {
        if (counter > n) {
            done = true;

            // Wake all waiting threads so they can exit.
            fizzSemaphore.release();
            buzzSemaphore.release();
            fizzBuzzSemaphore.release();
            numberSemaphore.release();

            return;
        }

        if (counter % 3 == 0 && counter % 5 == 0) {
            fizzBuzzSemaphore.release();
        } else if (counter % 3 == 0) {
            fizzSemaphore.release();
        } else if (counter % 5 == 0) {
            buzzSemaphore.release();
        } else {
            numberSemaphore.release();
        }
    }
}
