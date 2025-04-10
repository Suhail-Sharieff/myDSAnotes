package adv_java._11_Concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _04_exe_service {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*
        Runnable some_tasks[] = {
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Task1 Running..." + " using Thread-> " + Thread.currentThread().getName());
                    }
                },

                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Task2 Running..." + " using Thread-> " + Thread.currentThread().getName());
                    }
                },

                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Task3 Running..." + " using Thread-> " + Thread.currentThread().getName());
                    }
                },
        };

        // this will take care of execution of taks, we dont have to manually create
        // threads and hadle each
        ExecutorService executorService = Executors.newFixedThreadPool(some_tasks.length);
        for (Runnable task : some_tasks)
            executorService.execute(task);
        executorService.shutdown();

        */
        run_TestCases();

    }

    static void run_TestCases() throws InterruptedException, ExecutionException {
        List<Callable<Integer>>test_cases=List.of(
            ()->{ int cnt=0;try{while(cnt++!=10) Thread.sleep(1); }catch(Exception e){} return cnt;},

            ()->{ int cnt=0;try{while(cnt++!=1000) Thread.sleep(1); }catch(Exception e){} return cnt;},

            ()->{ int cnt=0;try{while(cnt++!=10) Thread.sleep(1); }catch(Exception e){} return cnt;}
        );

       ExecutorService executor = Executors.newFixedThreadPool(test_cases.size());
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        // Submit wrapped tasks with timing and ID
        for (int i = 0; i < test_cases.size(); i++) {
            int id = i;
            Callable<String> wrapped = () -> {
                long start = System.currentTimeMillis();
                int result = test_cases.get(id).call();
                long end = System.currentTimeMillis();
                long duration = end - start;
                return "Result of testcase-" + id + " is: " + result + " (Time taken: " + duration + " ms)";
            };
            completionService.submit(wrapped);
        }

        // Print results as they complete
        for (int i = 0; i < test_cases.size(); i++) {
            Future<String> future = completionService.take();
            System.out.println(future.get());
        }

        executor.shutdown();
    }
}
