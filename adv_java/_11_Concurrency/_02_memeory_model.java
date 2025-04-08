package adv_java._11_Concurrency;


public class _02_memeory_model {//demonstates race around condition:

    // in java , all threads share the same memory space, so they can access the
    // same variables and objects. This is called shared memory model.

    static int cnt = 0;// this is saved in head, so all threads can acess it

    public static void main(String[] args) throws InterruptedException {

        /*

        Race arouund 

        Thread t1=new Thread(()->{
            for(int i=0;i<1000000;i++) cnt++;
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<1000000;i++) cnt++;
        });

        t1.start();
        t2.start();


        t1.join();
        t2.join();

        System.out.println(cnt);
*/

        //solution:

        Thread t1=new Thread(()->{
            for(int i=0;i<1000000;i++) increment();
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<1000000;i++) increment();
        });

        t1.start();
        t2.start();


        t1.join();
        t2.join();

        System.out.println(cnt);
    }


    static synchronized void increment(){//meaning that a single thread will operate on it in one go
        cnt++;
    }
}
