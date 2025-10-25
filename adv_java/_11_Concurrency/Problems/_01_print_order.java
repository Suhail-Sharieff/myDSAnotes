package adv_java._11_Concurrency.Problems;

import java.util.concurrent.Semaphore;

/*1114. Print in Order
Solved
Easy
Topics
premium lock icon
Companies
Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().

Note:

We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seem to imply the ordering. The input format you see is mainly to ensure our tests' comprehensiveness.

 

Example 1:

Input: nums = [1,2,3]
Output: "firstsecondthird"
Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
Example 2:

Input: nums = [1,3,2]
Output: "firstsecondthird"
Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.
 

Constraints:

nums is a permutation of [1, 2, 3]. */
public class _01_print_order {

    class BruteForce {//brute fore becoz we are asking thread to wait thus we waste CPU cycles

        boolean done[];//or else can use turn variable too

        public BruteForce() {
            done = new boolean[3];
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            done[0] = true;
            notifyAll();
        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            synchronized (this) {
                while (!done[0]) this.wait();
                done[1] = true;
                printSecond.run();
                this.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (this) {
                while (!done[1]) this.wait();
                // printThird.run() outputs "third". Do not change or remove this line.
                done[2] = true;
                printThird.run();
                this.notifyAll();
            }
        }
    }


    //prereq for optimal(no CPU cycle waste in while loop): sem.acquire() will DECREMENT the number of permitted thread to access it by 1, sem.release() will do +1
    /*aquire():
     * Acquires a permit from this semaphore, blocking until one is available, or the thread is interrupted.

        Acquires a permit, if one is available and returns immediately, reducing the number of available permits by one.

        If no permit is available then the current thread becomes disabled for thread scheduling purposes and lies dormant until one of two things happens:

        Some other thread invokes the release method for this semaphore and the current thread is next to be assigned a permit; or
        Some other thread interrupts the current thread.-------------IMP--so soln works
     * 
    */
    class Optimal {//using 2 semaphores
        Semaphore run2, run3;

        public Optimal() {
            run2 = new Semaphore(0);//IMP not 1
            run3 = new Semaphore(0);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            run2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            run2.acquire();
            printSecond.run();
            run3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            run3.acquire(); 
            printThird.run();
        }
    }
}
