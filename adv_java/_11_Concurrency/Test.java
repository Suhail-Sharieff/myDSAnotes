package adv_java._11_Concurrency;

import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws Exception {

        ExecutorService es = Executors.newFixedThreadPool(3);

        CompletableFuture<String>cf=
        CompletableFuture
        .supplyAsync(()-> api1(),es)
        .thenApplyAsync((res1)->res1+api2(),es)
        .thenApplyAsync((res2)->res2+api3(),es);


        System.out.println(cf.get());
        




    }

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
}
