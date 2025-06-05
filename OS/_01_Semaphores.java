import java.util.concurrent.Semaphore;

public class _01_Semaphores{
    public static void main(String[] args) throws InterruptedException{

        Semaphore sm=new Semaphore(1);//permit 1 thread at a time
        
        Thread threds[]={
            new Thread(()->loop(sm),"t1"),
            new Thread(()->loop(sm),"t2"),
            new Thread(()->loop(sm),"t3"),
           
        };

        for(Thread e:threds) e.start();

        for(Thread e:threds) e.join();


        System.out.println(cnt);

    }


    static void loop(Semaphore sm) {
        for(int i=0;i<10;i++)
            try {
                increment(sm);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    private static int cnt=0;

    public static int increment(Semaphore sm) {
        try  {
            System.out.println("----");
            sm.acquire();
            cnt++;
            System.out.println(Thread.currentThread().toString());
            Thread.sleep(1000);
            sm.release();
            System.out.println("-----");
            return cnt;
        } catch (Exception e) {
        }
        return-1;
    }
}