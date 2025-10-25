# Concurrency in Java
## Thread
- is a light weight process 
- it is easy to create than compared to a complete process
- All threads of a process share same memory, so its important to ensure consistent concurrency when all/some threads are accessing that memory
## How to create a Thread 
- Method 1:
```java
class A extends Thread{
    @Override
    public void run(){
        System.out.println("Thread: "+threadId()+" is running...");
    }
}
//then in main method
void main(){
    for(int i=0;i<1000;i++){
            System.out.print(i+" ");
            new A().start();//not 'run()'
    }
}
```
this would start all 1000 threads simultaneously
- Method 2 `(more preferred)`
```java
class A implements Runnable{
    @Override
    public void run(){...}
}
//now in main
void main(){
    for(int i=0;i<1000;i++){
            System.out.print(i+" ");
            new A().run();//no start() avaialable here so use run()
    }
}
```
- Method 2 is more preferred because we can further extend that class in that, but it wouldnt be possible in method 1 since multiple inheritance isnt allowed in java
## run() VS start()
- The `run()` Method: Contains the code that will be executed when the thread runs. This is where you define what work the thread should perform.
- The `start()` Method: Initiates the thread and causes the JVM to call the run() method in a new thread of execution. Never call run() directly—this would execute the code in the current thread, defeating the purpose of threading.
- Both `run()` and `start()` are available when class extends a Thread but only `run()` is available for use when used Runnable method 
## Concurrency using ExecutorService
- This method is preferred in industry because in most cases its literally impossible to create many threads in a system, instead we perform task by using some fixed number of tasks
- Executor service will use the cached threads waiting to be assigned a work and will automatically 
```java
public static void main(String[] args) {
        ExecutorService es=Executors.newFixedThreadPool(4);
        for(int i=0;i<10;i++) {
            final int taksID=i;
            es.submit(()->{
                System.out.println(taksID+" executed by "+Thread.currentThread().getName());
            });
        }
        es.shutdown();
    }
```
output:
```out
1 executed by pool-1-thread-2
0 executed by pool-1-thread-1
3 executed by pool-1-thread-4
2 executed by pool-1-thread-3
4 executed by pool-1-thread-1
5 executed by pool-1-thread-2
6 executed by pool-1-thread-4
7 executed by pool-1-thread-3
8 executed by pool-1-thread-1
9 executed by pool-1-thread-2
```
## Example Race condition 
```java
static int cnt=0;
public static void main(String[] args) {
    for(int i=0;i<100;i++){
        new Thread(()->{
            cnt++;
        }).start();//if used run(), then meaning of concurrency is destroyed it just executes code without creating a thread so we will get 100, so use start()
    }
    System.out.println(cnt);//prints random values every time we run
}
```
## join() method

- When you call:
```java
int cnt=0;
Thread t=new Thread(incrementCntby100())
thread.join();//without this the cnt will get increment actually but the below print stmt may not print correct value of cnt, so we ask main method to not exit until and unless the main thread has finished completion
println(cnt);
```
it means:
``Main thread, wait here until this thread has finished running.``
So join() makes the main thread (or the thread calling it) pause until the other thread completes.
## Solving race condition for above eg
- Method 1 : `synchronized`, allows only 1 thread to access critical space at a time, hence other threads must wait hence wasting CPU cycles, use when the critical section is too small, else use locks 
```java
    static int cnt = 0;
    public static void main(String[] args) {
        Thread arr[]=new Thread[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=new Thread(() -> {
                increment();
            });
            arr[i].start();
        }
        for(var e:arr) e.join();//IMP coz we wanna print updated val
        System.out.println(cnt);
    }

    static synchronized void increment(){//ensures only 1 thred can access this method at a time
        cnt++;
    }
```
- Method 2: `volatile`, when a variable is declared as volatile, any changes to it are automatically known to all threads accessing it without us having to explicitly declare a lock, but use it when it has extrememely less limited number of possible values(NOT ideal to use with cnt here since cnt can take any values) for ex u can use it with `boolean` since it can either have a true or a false, since it is efficient to use for vars that can have very few values only, `volatile` ensures `visibility` but not   `atomicity`.
```java
    static  volatile int cnt = 0;
    public static void main(String[] args){
        Thread arr[]=new Thread[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=new Thread(() -> {
                increment();
            });
            arr[i].start();
        }
        for(var e:arr) e.join();
        System.out.println(cnt);
    }

    static void increment(){//no need of synchromized now,------highly inefficient
        cnt++;
    }
```
- Method 3: `synchromized`+`classic lock`
```java
static  int cnt = 0;
    static final Object myLock=new Object();
    public static void main(String[] args) {
        Thread arr[]=new Thread[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=new Thread(() -> {
                increment();
            });
            arr[i].start();
        }
        for(var e:arr) e.join();
        System.out.println(cnt);
    }

    static void increment(){
       synchronized(myLock){
          cnt++;
       }
    }
```
- Method 4: more verbose `ReentrantLock`
```java

    static ReentrantLock lock = new ReentrantLock(true);
    static int cnt = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread arr[]=new Thread[100];
        for(int i=0;i<arr.length;i++){
            arr[i]=new Thread(Test::increment);
            arr[i].start();
        }
        for(var t:arr) t.join();
        System.out.println(cnt);
    }


    static void increment(){
        lock.lock();
        try{
            System.out.println("Thread name: "+Thread.currentThread().getName()+" cnt="+cnt);
            cnt++;
        }finally{
            lock.unlock();
        }
    }
```
- Method 5: Using `Semaphore`
```java

public class Test {

    static int cnt=0;
    static Semaphore sem=new Semaphore(1);//that number is number of threads that can lock the cnt at a time, if 1 it acts as binary semaphore

    public static void main(String[] args) throws InterruptedException {
        Thread arr[]=new Thread[100];

        for(int i=0;i<arr.length;i++){
            arr[i]=new Thread(Test::increment);
            arr[i].start();
        }

        for(var e:arr) e.join();;


        System.err.println(cnt);

    }

    static void increment(){
        try{
            sem.acquire();
            System.out.println("Thread name: "+Thread.currentThread().getName()+" cnt="+cnt);
            cnt++;
        }catch(Exception e){
            System.err.println("Failed to lock");
        }finally{
            sem.release();
        }        
    }
}
```
- Method 6: `ReadWriteLock`, best when reads>>writes
```java
public class ReadWriteLockExample {
    static int cnt = 0;
    static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    static final Lock writeLock = rwLock.writeLock();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(ReadWriteLockExample::increment);
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println(cnt);
    }

    static void increment() {
        writeLock.lock();
        try {
            cnt++;
        } finally {
            writeLock.unlock();
        }
    }
}
```
- Method 7: `StampedLock`, optimized for very heavy reads
```java
import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    static int cnt = 0;
    static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(StampedLockExample::increment);
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println(cnt);
    }

    static void increment() {
        long stamp = lock.writeLock();
        try {
            cnt++;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}

```
- Method 8: `CountdownLatch`, to use without join(), waits for each thread
```java
import java.util.concurrent.CountDownLatch;

public class LatchExample {
    static int cnt = 0;
    static final Object lock = new Object();
    static final int THREADS = 100;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(THREADS);
        for (int i = 0; i < THREADS; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    cnt++;
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(cnt);
    }
}
```
## Asynchronous programming using `CompletableFuture`
- Traditional async programming was that we create Future object for each API, this worked fine, but the thing is here we cant control the sequence of API calls, for ex
```java
        ExecutorService es=Executors.newFixedThreadPool(3);
        Future<String>step1=es.submit(()->{
            Thread.sleep(2000);
            return "Step 1 complete -> ";
        });
        Future<String>step2=es.submit(()->{
            Thread.sleep(2000);
            return "Step 2 complete ->";
        });
        Future<String>step3=es.submit(()->{
            Thread.sleep(2000);
            return "Step 3 complete";
        });
        
        String overalResult=step1.get()+step2.get()+step3.get();//all three since are submitted runs parallelly and fetchedm but the order in whcih they are fetched cant be controlled, also its blocking

        System.out.println(overalResult);

        es.close();
```
this problem can be solved using `CompletableFuture`
- CompletableFuture lets you:
Run tasks asynchronously.
Chain multiple tasks together.
Combine results of multiple futures.
Handle errors gracefully without try-catch chaos.
Write code that reads like a pipeline, rather than a tangled mess of threads.
- Say my 3 APIs are:
```java
static String api1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Step 1 complete");
        return "OK1";
    }

    static String api2() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("Step 2 complete");
        return "OK2";
    }

    static String api3() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("Step 3 complete");
        return "OK3";
    }
```
- Case 1: APIs are independent of each other ie can be made run parallelely and fetch result 
```java
        ExecutorService es = Executors.newFixedThreadPool(3);

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(Test::api1, es);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(Test::api2, es);
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(Test::api3, es);

        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);

        all.thenRun(() -> {
            System.out.println("All done: " +
                f1.join() + "->" + f2.join() + "->" + f3.join());
        }).join();

        es.shutdown();

        //output:
        /*
        Step 1 complete
        Step 3 complete  <-----3 bfr 2 since it has less burst time
        Step 2 complete
        All done: OK1->OK2->OK3
        */
```
- Case 2: U want to run the asynchronous operations in specific order(say 1->2->3), ie if APIS are dependednt on each otherand hence cant run paralleley
```java
        ExecutorService es = Executors.newFixedThreadPool(3);

        CompletableFuture<String>cf=
        CompletableFuture
        .supplyAsync(()-> api1(),es)
        .thenApplyAsync((res1)->res1+api2(),es)
        .thenApplyAsync((res2)->res2+api3(),es);

        System.out.println(cf.get());
        
//output:
/*
Step 1 complete
Step 2 complete
Step 3 complete
OK1OK2OK3
*/
```

## Deadlock example
Alphonse and Gaston are friends, and great believers in courtesy. A strict rule of courtesy is that when you bow to a friend, you must remain bowed until your friend has a chance to return the bow. Unfortunately, this rule does not account for the possibility that two friends might bow to each other at the same time. This example application, Deadlock, models this possibility:

```java
public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                + "  has bowed to me!%n", 
                this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
            new Friend("Alphonse");
        final Friend gaston =
            new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}
```
- Assume 2 boxes A and B each having bow() and bowBack() method, when both simultaneously call bow(), A locks his obj and so do B for his, now when A has bowed, he is trying to access B's bowback, but since its locked by B himself he will wait, same condition for B, so there is a deadlock
- When Deadlock runs, it's extremely likely that both threads will block when they attempt to invoke bowBack. Neither block will ever end, because each thread is waiting for the other to exit bow.
## Example for `notify()`, `notifyAll()`,`wait()` usage
- Imagine 2 threads, one thread should permit the other to enjoy, the other should wait unless granted permission, once another thread sets permission to true, use notify() to inform `changes of attributes of the curr class`(in this case its `gotPermissionToEnjoy`)to all other threads(notifyAll()) or only 1 thread (notify())
```java
class Person {
    boolean gotPermissionToEnjoy;

    public synchronized void startEnjoying() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is waiting for some other thread to permit him enjoy");
        while (!gotPermissionToEnjoy)
            wait();
        System.out.format("%s is happy now!\n", Thread.currentThread().getName());
    }

    public synchronized void permitToEnjoy() {
        this.gotPermissionToEnjoy = true;
        notify();//<-----------<IMP>
        System.out.println(Thread.currentThread().getName() + " permitted to enjoy!");
    }
}
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Person p = new Person();
        new Thread(() -> {
            try {
                p.startEnjoying();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Student").start();


        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.permitToEnjoy();
        },"Teacher").start();
    }

}
/*output:

Student is waiting for some other thread to permit him enjoy
Teacher permitted to enjoy!
Student is happy now!


*/
```
