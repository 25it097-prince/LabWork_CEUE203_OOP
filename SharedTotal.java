import java.util.concurrent.atomic.AtomicLong;
public class SharedTotal {
    static final int SIZE = 10_000_000;
    static final int THREADS = 4;
    static final long[] numbers = new long[SIZE];

    static {
        for (int i = 0; i < SIZE; i++)
            numbers[i] = 1;
    }

    static class WrongTotal {
        long total = 0;
        void add(long value) {
            total += value;
        }
    }

    static class SynchronizedTotal {
        long total = 0;
        synchronized void add(long value) {
            total += value;
        }
    }

    static class AtomicTotal {
        AtomicLong total = new AtomicLong(0);
        void add(long value) {
            total.addAndGet(value);
        }
    }

    interface Task {
        void run(int start, int end);
    }

    static Thread[] createThreads(Task task) {
        Thread[] threads = new Thread[THREADS];
        int part = SIZE / THREADS;

        for (int i = 0; i < THREADS; i++) {
            int start = i * part;
            int end = (i == THREADS - 1) ? SIZE : start + part;
            final int s = start, e = end;

            threads[i] = new Thread(() -> task.run(s, e));
        }
        return threads;
    }

    static void startAndJoin(Thread[] threads)
            throws InterruptedException {
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
    }

    public static void main(String[] args) throws InterruptedException {
        long expected = SIZE;

        WrongTotal wrong = new WrongTotal();
        long start = System.nanoTime();

        startAndJoin(createThreads((s, e) -> {
            for (int i = s; i < e; i++)
                wrong.add(numbers[i]);
        }));

        long end = System.nanoTime();

        System.out.println("Expected total = " + expected);
        System.out.println("\n1. WITHOUT synchronization");
        System.out.println("Total = " + wrong.total);
        System.out.println("Time  = " + (end - start) / 1_000_000.0 + " ms");

        SynchronizedTotal sync = new SynchronizedTotal();
        start = System.nanoTime();

        startAndJoin(createThreads((s, e) -> {
            for (int i = s; i < e; i++)
                sync.add(numbers[i]);
        }));

        end = System.nanoTime();

        System.out.println("\n2. WITH synchronized");
        System.out.println("Total = " + sync.total);
        System.out.println("Time  = " + (end - start) / 1_000_000.0 + " ms");

        AtomicTotal atomic = new AtomicTotal();
        start = System.nanoTime();

        startAndJoin(createThreads((s, e) -> {
            for (int i = s; i < e; i++)
                atomic.add(numbers[i]);
        }));

        end = System.nanoTime();

        System.out.println("\n3. WITH AtomicLong");
        System.out.println("Total = " + atomic.total.get());
        System.out.println("Time  = " + (end - start) / 1_000_000.0 + " ms");

        System.out.println("\nBoth fixes should produce the correct total.");
        System.out.println("Difference is compared using execution time.");
    }
}