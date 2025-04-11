package adv_java._11_Concurrency;
/* 455

A semaphore is a programming concept that is frequently used to solve multi-threading problems. My question to the community:

What is a semaphore and how do you use it?


Think of semaphores as bouncers at a nightclub. There are a dedicated number of people that are allowed in the club at once. If the club is full no one is allowed to enter, but as soon as one person leaves another person might enter.

It's simply a way to limit the number of consumers for a specific resource. For example, to limit the number of simultaneous calls to a database in an application.*/

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class _05_semaphore {
    public static void main(String[] args) throws InterruptedException {
        Semaphore smp=new Semaphore(2, false);
        System.out.println("No of threads that can use my resource at a time = "+smp.availablePermits());

        System.out.println("Occupyinh 1 thread for other task.......");
        smp.acquire();

        Thread.sleep(3000);//some heavy opr

        System.out.println("Heavy operation done, releasing that thread.....");
        smp.release();
        System.out.println("New No of threads that can use my resource at a time = "+smp.availablePermits());
        

        ReentrantLock rl=new ReentrantLock();

        System.out.println(rl.getHoldCount());
        rl.lock();rl.lock();rl.lock();//lock 3 times
        System.out.println(rl.getHoldCount());
        rl.unlock();rl.unlock();//but unlock 2 times
        System.out.println(rl.getHoldCount());

    }
}
