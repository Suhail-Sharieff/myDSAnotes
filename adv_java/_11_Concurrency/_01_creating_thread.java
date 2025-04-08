package adv_java._11_Concurrency;

public class _01_creating_thread {
    private static class way1 extends Thread{
        @Override
        public void run() {
            System.err.println("Thread running by extending thread");
        }
    }
    private static class way2 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread running by implementing  Runnable 1");
        }
    }


    /*
/*
 💡 Key Differences Summary between 'extends Thread' and 'implements Runnable':

 ┌───────────────────────────────┬────────────────────────────┬──────────────────────────────┐
 │          Feature              │      extends Thread         │     implements Runnable      │
 ├───────────────────────────────┼────────────────────────────┼──────────────────────────────┤
 │ Inheritance                   │ ❌ Can’t extend another class │ ✅ Can extend another class   │
 │ Reusability                   │ 🔁 Less reusable             │ 🔁 Highly reusable            │
 │ Separation of concerns        │ 😕 Not well separated        │ ✅ Clean separation (task/thread) │
 │ Recommended approach?         │ 🚫 No (unless customizing)   │ ✅ Yes                         │
 └───────────────────────────────┴────────────────────────────┴──────────────────────────────┘

 ✅ When to Use What?

 ┌──────────────────────────────────────────────────────────────┬────────────────────────────┐
 │                       Scenario                               │     Recommendation         │
 ├──────────────────────────────────────────────────────────────┼────────────────────────────┤
 │ You just want to run a thread with simple logic              │ Either is okay, prefer Runnable │
 │ You need your class to extend another class                  │ Use Runnable               │
 │ You’re writing reusable task logic                           │ Use Runnable               │
 │ You’re customizing thread behavior (e.g., priority, group)   │ Maybe extend Thread        │
 └──────────────────────────────────────────────────────────────┴────────────────────────────┘
*/


    public static void main(String[] args) {
        way1 w1=new way1();w1.start();

        Thread using_runnable_01=new Thread(new way2()); 
        using_runnable_01.run();    // ❌ Runs on main thread (no new thread) If you call run() directly, your thread code will run like any other method — it won't be concurrent.
        using_runnable_01.start();  // ✅ Runs on a new thread


        Thread using_runnable_02=new Thread(()->{System.out.println("Thread running using Runnable 2");});
        using_runnable_02.start();


        Runnable rr=()->{
            for(int i=0;i<4;i++) {
                final int t=i;
                new Thread(()->{System.out.println("Hello world "+t);}).start();;
            }
        };
        rr.run();
         //In Computer we have 3 kinds of threads namely OS thread, platform thread and virtual thread.
    //OS thread is the one which is created by OS and it is a native thread. It is also called as kernel thread.
    //Platform thread is the one which is created by JVM and it is a native thread. It is also called as user thread.   
    //Virtual thread is the one which is created by JVM and it is a lightweight thread. It is also called as user thread.
    // The virtual threads are created by the JVM and they are lightweight threads. They are used to create a large number of threads without consuming a lot of memory. The virtual threads are created by the JVM and they are lightweight threads. They are used to create a large number of threads without consuming a lot of memory. The virtual threads are created by the JVM and they are lightweight threads. They are used to create a large number of threads without consuming a lot of memory. They r queued up, whenver a platform thread is free it picks up whatsover the virtual thread
      Thread v1=Thread.ofVirtual().start(()->{System.out.println("Hello world virtual thread");});
      try {
        v1.join();//if this is not written, then the main thread will terminate before the virtual thread is executed.
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } //start() method
    }


   
}
