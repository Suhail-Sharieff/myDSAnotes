import java.util.concurrent.locks.ReentrantLock;
/*

 */
public class _04_dining_philosopher {

    public static void main(String[] args) {
        int nPhilosophers = 5;
        new DiningPhilosophers(nPhilosophers).startDining();
    }

    static class DiningPhilosophers {

        private final ReentrantLock[] forks;
        private final Philosopher[] philosophers;

        public DiningPhilosophers(int n) {
            forks = new ReentrantLock[n];
            for (int i = 0; i < n; i++) {
                forks[i] = new ReentrantLock();
            }
            philosophers = new Philosopher[n];
            for (int i = 0; i < n; i++) {
                // left fork is i, right fork is (i+1)%n
                philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % n]);
            }
        }

        public void startDining() {
            for (Philosopher p : philosophers) {
                new Thread(p, "Philosopher-" + p.id).start();
            }
        }

        static class Philosopher implements Runnable {
            final int id;
            private final ReentrantLock firstFork;
            private final ReentrantLock secondFork;

            Philosopher(int id, ReentrantLock leftFork, ReentrantLock rightFork) {
                this.id = id;
                // break the cycle: odd pick right first, even pick left first
                if (id % 2 == 0) {
                    firstFork = leftFork;
                    secondFork = rightFork;
                } else {
                    firstFork = rightFork;
                    secondFork = leftFork;
                }
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        think();
                        pickUpForks();
                        eat();
                        putDownForks();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            private void think() throws InterruptedException {
                System.out.println(id + " is THINKING");
                Thread.sleep((long) (Math.random() * 100));
            }

            private void pickUpForks() {
                firstFork.lock();
                System.out.println(id + " picked up " +
                pos_of_fork(true));
                secondFork.lock();
                System.out.println(id + " picked up " +
                        pos_of_fork(false));
            }

            private void eat() throws InterruptedException {
                System.out.println(id + " is EATING");
                Thread.sleep((long) (Math.random() * 1000));
            }

            private void putDownForks() {
                firstFork.unlock();
                System.out.println(id + " put down "+pos_of_fork(true));
                secondFork.unlock();
                System.out.println(id + " put down "+pos_of_fork(false));
            }

            // Helper to figure out which lock is left/right
            private String pos_of_fork(boolean talking_abt_first_fork){
                if(id%2==0){
                    //then fisrt fork is left form
                    if(talking_abt_first_fork) return "LEFT fork";
                    return "RIGHT fork";
                }
                //else first fork is right fork
                if(talking_abt_first_fork) return "RIGHT fork";
                return "LEFT fork";
            }
            
        }

        // We need a way for Philosopher to see the forks array
        
    }
}
