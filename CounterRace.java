public class CounterRace {
    static class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public synchronized void synchronizedIncrement() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    static final int THREADS = 10;
    static final int INCREMENTS = 100000;

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++)
                    counter.increment();
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        int expected = THREADS * INCREMENTS;

        System.out.println("WITHOUT synchronization:");
        System.out.println("Expected count = " + expected);
        System.out.println("Actual count   = " + counter.getCount());

        counter = new Counter();

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++)
                    counter.synchronizedIncrement();
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("\nWITH synchronization:");
        System.out.println("Expected count = " + expected);
        System.out.println("Actual count   = " + counter.getCount());
    }
}