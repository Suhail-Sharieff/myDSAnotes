import java.util.ArrayList;
import java.util.List;

public class _02_Producer_Consumer {

    static class PC {
        public PC() {
            this.MAX_SZ = 3;
            list = new ArrayList<>();
        }

        private int MAX_SZ;
        private List<Integer> list;

        synchronized void produce() throws InterruptedException {
            while (true) {
                if (list.size() == MAX_SZ) {
                    System.out.println("List is full, producer is waiting...");
                    notifyAll();
                    wait();
                }
                System.out.println(Thread.currentThread().getName());
                list.add(12);
                notifyAll();//notifyAll() inplace of notify() becoz what if there r multiple consumers/producers working on the same list
                Thread.sleep(1000);
            }
        }

        synchronized void consume() throws InterruptedException {
            while (true) {
                if (list.isEmpty()) {
                    System.out.println("List is empty, Cosnumer is waiting...");
                    notifyAll();
                    wait();
                }
                System.out.println(Thread.currentThread().getName());
                list.removeLast();
                notifyAll();
                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PC obj = new PC();
        Thread producerThread = new Thread(() -> {
            try {
                obj.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer thread");
        Thread consumerThread = new Thread(() -> {
            try {
                obj.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer thread");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

    }
}
