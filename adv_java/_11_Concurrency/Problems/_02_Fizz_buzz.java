package adv_java._11_Concurrency.Problems;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class _02_Fizz_buzz {



    //------------using Atomicinteger
    class Meth1 {
        private int n;
        private AtomicInteger current = new AtomicInteger(1);

        public Meth1(int n) {
            this.n = n;
        }

        public void fizz(Runnable printFizz) throws InterruptedException {
            while (true) {
                int i = current.get();
                if (i > n) break;
                if (i % 3 == 0 && i % 5 != 0) {
                    printFizz.run();
                    current.incrementAndGet();
                }
            }
        }

        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (true) {
                int i = current.get();
                if (i > n) break;
                if (i % 5 == 0 && i % 3 != 0) {
                    printBuzz.run();
                    current.incrementAndGet();
                }
            }
        }

        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (true) {
                int i = current.get();
                if (i > n) break;
                if (i % 15 == 0) {
                    printFizzBuzz.run();
                    current.incrementAndGet();
                }
            }
        }

        public void number(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                int i = current.get();
                if (i > n) break;
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                    current.incrementAndGet();
                }
            }
        }
    }


    //--------------------using synchromized

    class Method2 {
        private final int n;
        private int i = 1;

        public Method2(int n) {
            this.n = n;
        }

        public synchronized void fizz(Runnable printFizz) throws InterruptedException {
            while (i<=n) {
                while (i <= n && !(i % 3 == 0 && i % 5 != 0)) {
                    wait();
                }
                if (i > n) break;
                printFizz.run();
                i++;
                notifyAll();
            }
        }

        public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
            while (i<=n) {
                while (i <= n && !(i % 5 == 0 && i % 3 != 0)) {
                    wait();
                }
                if (i > n) break;
                printBuzz.run();
                i++;
                notifyAll();
            }
        }

        public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (i<=n) {
                while (i <= n && !(i % 15 == 0)) { // simpler check
                    wait();
                }
                if (i > n) break;
                printFizzBuzz.run();
                i++;
                notifyAll();
            }
        }

        public synchronized void number(IntConsumer printNumber) throws InterruptedException {
            while (i<=n) {
                while (i <= n && (i % 3 == 0 || i % 5 == 0)) {
                    wait();
                }
                if (i > n) break;
                printNumber.accept(i++);
                notifyAll();
            }
        }
    }

}
