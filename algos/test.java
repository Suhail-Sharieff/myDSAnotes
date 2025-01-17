public class test {

    public static void main(String[] args) {
        Temp t=new Temp();
        Temp t2=new Temp();
        Thread th=new Thread();
        t.start();
        t2.start();
    }
}

class Temp extends Thread{
    @Override
    public void run(){
        for(int i=0;i<=10;i++){
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}