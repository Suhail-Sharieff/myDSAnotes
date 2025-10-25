package adv_java._11_Concurrency;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Person p=new Person("Suhail");
        new Thread(()->{try {
            p.startEnjoying();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}).start();
        new Thread(()->{try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }p.permitToEnjoy();}).start();;
    }
    
}

class Person{
    boolean gotPermissionToEnjoy;

    public synchronized void startEnjoying() throws InterruptedException{
        System.out.println("None made me happy");
        while(!gotPermissionToEnjoy) wait();
        System.out.format("%s is happy now!\n",Thread.currentThread().getName());
    }
    public synchronized void permitToEnjoy(){this.gotPermissionToEnjoy=true;notify();System.out.println();}
}