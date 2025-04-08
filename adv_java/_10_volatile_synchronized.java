package adv_java;

import java.time.Duration;

/*
In Java, volatile and synchronized are both used for managing concurrency, but they serve different purposes:
volatile: This keyword ensures that all threads see a consistent value of a variable. It prevents caching of the variable's value, meaning that any write to a volatile variable is immediately visible to other threads. However, it does not provide mutual exclusion(Mutual exclusion is a property of process synchronization that ensures that no two processes can be in the critical section at the same time. It is a key concept in concurrency control, designed to prevent race conditions by allowing only one process to access shared resources at any given moment. In distributed systems, mutual exclusion prevents simultaneous operations from concurrently using common assets.), so it cannot be used to protect critical sections of code. 
3
synchronized: This keyword is used to lock a method or block of code, ensuring that only one thread can execute it at a time. This provides both visibility and atomicity, making it suitable for protecting shared resources. However, it can lead to performance overhead due to locking. 
2

In summary, use volatile for visibility without locking, and synchronized for mutual exclusion and thread safety. 
3
 */

/*
1114. Print in Order
Solved
Easy
Topics
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

nums is a permutation of [1, 2, 3].
 */

class Using_Synchronized {
    class Foo {

        private int turn;

        public Foo() {
            turn = 1;
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {
            while (turn != 1)
                wait();
            printFirst.run();
            turn = 2;
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while (turn != 2)
                wait();
            printSecond.run();
            turn = 3;
            notifyAll();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (turn != 3)
                wait();
            printThird.run();
        }
    }
}

class Using_Volatile {

    class Foo {

        private static volatile int turn;

        public Foo() {
            turn = 1;
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            while (turn != 1)
                Thread.sleep(Duration.ofNanos(1));
            printFirst.run();
            turn = 2;
        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            while (turn != 2)
                Thread.sleep(Duration.ofNanos(1));
            printSecond.run();
            turn = 3;
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            while (turn != 3)
                Thread.sleep(Duration.ofNanos(1));
            printThird.run();
        }
    }
}