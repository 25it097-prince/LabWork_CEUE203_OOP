class DeadLock {
    static Object lockA = new Object();
    static Object lockB = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 1: Locked A");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                synchronized (lockB) {
                    System.out.println("Thread 1: Locked B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread 2: Locked B");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                synchronized (lockA) {
                    System.out.println("Thread 2: Locked A");
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(1000);

        System.out.println("Deadlock occurred!");

        System.out.println("\nRemoving deadlock with consistent lock order:");

        Thread t3 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 3: Locked A");

                synchronized (lockB) {
                    System.out.println("Thread 3: Locked B");
                }
            }
        });

        Thread t4 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 4: Locked A");

                synchronized (lockB) {
                    System.out.println("Thread 4: Locked B");
                }
            }
        });

//        t3.start();
//        t4.start();
//
//        t3.join();
//        t4.join();
    }
}


