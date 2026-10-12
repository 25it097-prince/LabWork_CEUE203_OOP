public class SeatBookingRace {
    static class BookingSystem {
        private int seatsLeft = 5;
        private int successfulBookings = 0;

        public boolean bookWithoutSync() {
            if (seatsLeft > 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                seatsLeft--;
                successfulBookings++;
                return true;
            }
            return false;
        }

        public synchronized boolean book() {
            if (seatsLeft > 0) {
                seatsLeft--;
                successfulBookings++;
                return true;
            }
            return false;
        }

        public int getSeatsLeft() {
            return seatsLeft;
        }

        public int getSuccessfulBookings() {
            return successfulBookings;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 10;
        BookingSystem system = new BookingSystem();
        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                if (system.bookWithoutSync())
                    System.out.println(Thread.currentThread().getName()
                            + " booked a seat.");
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("\nWITHOUT synchronization:");
        System.out.println("Seats left = " + system.getSeatsLeft());
        System.out.println("Successful bookings = "
                + system.getSuccessfulBookings());

        system = new BookingSystem();

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                if (system.book())
                    System.out.println(Thread.currentThread().getName()
                            + " booked a seat.");
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("\nWITH synchronization:");
        System.out.println("Seats left = " + system.getSeatsLeft());
        System.out.println("Successful bookings = "
                + system.getSuccessfulBookings());
    }
}