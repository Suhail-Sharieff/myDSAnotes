package adv_java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class _02_callable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> someHeavyOperation=()->{
            int k=0;
            Thread.sleep(3000);
            for(int i=0;i<10;i++) k=i;
            return k;
        };

        //asssign that heavy opeartion to separate thread
        FutureTask<Integer>task=new FutureTask<>(someHeavyOperation);
        new Thread(task).start();

        System.out.println("im executed first becoz some other thread is doinh thatHeavyOperation");

        int result=task.get();

        System.out.println(result);
        
        
    }
}
