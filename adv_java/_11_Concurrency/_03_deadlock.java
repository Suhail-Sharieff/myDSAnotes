package adv_java._11_Concurrency;



public class _03_deadlock {
    static Object Suhail_key=new Object();
    static Object Sharieff_key=new Object();
    public static void main(String[] args) {
        Thread suhail_thread=new Thread(()->{
            synchronized(Sharieff_key){
                System.out.println("Suhail got Sharieff's Key, now he will wait Sharieff to give his key");
                synchronized(Suhail_key){
                    System.out.println("Suhail waiting for his Key");
                }
            }
        });
        Thread sharieff_thread=new Thread(()->{
            synchronized(Suhail_key){
                System.out.println("Sharieff got Suhail's Key, now he will wait Suhail to give his key");
                synchronized(Sharieff_key){
                    System.out.println("Sharieff waiting for his Key");
                }
            }
        });

        suhail_thread.start();
        sharieff_thread.start();
    }
}
