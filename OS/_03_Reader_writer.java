import java.util.concurrent.Semaphore;

public class _03_Reader_writer {

    static class MyData {
        private int data;
        public void setData(int newValue) { this.data = newValue; }
        public int getData() { return this.data; }
    }

    static Semaphore writerMutex = new Semaphore(1);
    static Semaphore readerMutex = new Semaphore(1);
    static int readerCount = 0;

    static class MyDataReader implements Runnable {
        MyData dataObj;
        public MyDataReader(MyData dataObj) { this.dataObj = dataObj; }
        @Override
        public void run() {
            try {
                readerMutex.acquire();

                readerCount++;
                if(readerCount==1) writerMutex.acquire();

                readerMutex.release();//i will give chance for other reader to join me, i dont want to make them wait untill i print

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" read "+dataObj.getData());

                //i hvae read and print data, i need to exit my critical section
                readerMutex.acquire();
                readerCount--;
                if(readerCount==0) writerMutex.release();
                readerMutex.release();
                

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class MyDataWriter implements Runnable {
        MyData dataObj;
        public MyDataWriter(MyData dataObj) { this.dataObj = dataObj; }
        @Override
        public void run() {
            try {
                writerMutex.acquire();
                System.out.println(Thread.currentThread().getName() + " writing...");
                Thread.sleep(1000);
                dataObj.setData(23); writerMutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyData data = new MyData();

        Thread reader1_thread = new Thread(new MyDataReader(data), "Reader1");
        Thread reader2_thread = new Thread(new MyDataReader(data), "Reader2");
        Thread writer1_thread = new Thread(new MyDataWriter(data), "Writer1");
        Thread writer2_thread = new Thread(new MyDataWriter(data), "Writer2");

        reader1_thread.start();
        reader2_thread.start();
        writer1_thread.start();
        writer2_thread.start();

        reader1_thread.join();
        reader2_thread.join();
        writer1_thread.join();
        writer2_thread.join();
    }
}