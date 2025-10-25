package adv_java._11_Concurrency;

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
        notify();
        System.out.println(Thread.currentThread().getName() + " permitted to enjoy!");
    }
}