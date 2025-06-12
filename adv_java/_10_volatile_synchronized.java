package adv_java;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

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

// =================FOLLOW UP:
// Fizz Buzz Multithread Leetcode 1195
class FizzBuzz {
    private int n;
    private int curr = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (curr <= n) {
            if (curr % 3 == 0 && curr % 5 != 0) {
                printFizz.run();
                curr++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (curr <= n) {
            if (curr % 3 != 0 && curr % 5 == 0) {
                printBuzz.run();
                curr++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (curr <= n) {
            if (curr % 15 == 0) {
                printFizzBuzz.run();
                curr++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (curr <= n) {
            if (curr % 3 != 0 && curr % 5 != 0) {
                printNumber.accept(curr);
                curr++;
                notifyAll();
            } else {
                wait();
            }
        }
    }


    //----------------USING SEEMAPHORES:
    static class FizzBuzz2 {
    private int n;
    private int i;
    private Semaphore incrementer;
    private Semaphore fizz;
    private Semaphore buzz;
    private Semaphore fizz_buzz;

    public FizzBuzz2(int n) {
        this.n = n;
        i = 1;
        incrementer = new Semaphore(1);
        fizz = new Semaphore(0);
        buzz = new Semaphore(0);
        fizz_buzz = new Semaphore(0);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            fizz.acquire();
            if (i > n)
                break;
            printFizz.run();
            release_suitable_lock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            buzz.acquire();
            if (i > n)
                break;
            printBuzz.run();
            release_suitable_lock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            fizz_buzz.acquire();
            if (i > n)
                break;
            printFizzBuzz.run();
            release_suitable_lock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            incrementer.acquire();
            if (i > n)
                break;
            printNumber.accept(i);
            release_suitable_lock();
        }//n operations ho chuke hei
        fizz.release();
        buzz.release();
        fizz_buzz.release();
        incrementer.release();
    }

    public void release_suitable_lock() {
        i++;
        if (i <= n) {
            if (i % 3 == 0 && i % 5 == 0)
                fizz_buzz.release();
            else if (i % 3 == 0 && i % 5 != 0)
                fizz.release();
            else if (i % 5 == 0 && i % 3 != 0)
                buzz.release();
            else if (i % 5 != 0 && i % 3 != 0)
                incrementer.release();
        } else {
            //subko allow karo out of critical section
            fizz.release();
            buzz.release();
            fizz_buzz.release();
            incrementer.release();
        }
    }
}
}


/*
1195. Fizz Buzz Multithreaded
Solved
Medium
Topics
Companies
You have the four functions:

printFizz that prints the word "fizz" to the console,
printBuzz that prints the word "buzz" to the console,
printFizzBuzz that prints the word "fizzbuzz" to the console, and
printNumber that prints a given integer to the console.
You are given an instance of the class FizzBuzz that has four functions: fizz, buzz, fizzbuzz and number. The same instance of FizzBuzz will be passed to four different threads:

Thread A: calls fizz() that should output the word "fizz".
Thread B: calls buzz() that should output the word "buzz".
Thread C: calls fizzbuzz() that should output the word "fizzbuzz".
Thread D: calls number() that should only output the integers.
Modify the given class to output the series [1, 2, "fizz", 4, "buzz", ...] where the ith token (1-indexed) of the series is:

"fizzbuzz" if i is divisible by 3 and 5,
"fizz" if i is divisible by 3 and not 5,
"buzz" if i is divisible by 5 and not 3, or
i if i is not divisible by 3 or 5.
Implement the FizzBuzz class:

FizzBuzz(int n) Initializes the object with the number n that represents the length of the sequence that should be printed.
void fizz(printFizz) Calls printFizz to output "fizz".
void buzz(printBuzz) Calls printBuzz to output "buzz".
void fizzbuzz(printFizzBuzz) Calls printFizzBuzz to output "fizzbuzz".
void number(printNumber) Calls printnumber to output the numbers.
 

Example 1:

Input: n = 15
Output: [1,2,"fizz",4,"buzz","fizz",7,8,"fizz","buzz",11,"fizz",13,14,"fizzbuzz"]
Example 2:

Input: n = 5
Output: [1,2,"fizz",4,"buzz"]
 

Constraints:

1 <= n <= 50
 */
public class _10_volatile_synchronized {

    public static void main(String[] args) throws Exception {

        FizzBuzz fizzBuzz = new FizzBuzz(15);

        Thread t1 = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.print("fizz "));
            } catch (InterruptedException e) {
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.print("buzz "));
            } catch (InterruptedException e) {
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.print("fizzbuzz "));
            } catch (InterruptedException e) {
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                fizzBuzz.number((x) -> System.out.print(x + " "));
            } catch (InterruptedException e) {
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}