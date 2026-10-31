import java.lang.Thread;
import java.util.Scanner;
class SharedBuffer {
    private int item;
    private boolean isavailable = false;

    public synchronized void produce(int value) {
        try {
            while (isavailable) {
                wait(1000);
            }

            item = value;
            System.out.println("Produced: " + item);

            isavailable = true;
            notify();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized int consume() {
        try {
            while (!isavailable) {
                wait(1000);
            }

            int value = item;
            System.out.println("Consumed: " + value);

            isavailable = false;
            notify();

            return value;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1;
        }
    }
}

class Producer extends Thread {
    private SharedBuffer buffer;
    private int count;

    Producer(SharedBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {
        for (int i = 1; i <= count; i++) {
            buffer.produce(i);
        }
    }
}

class Consumer extends Thread {
    private SharedBuffer buffer;
    private int count;

    Consumer(SharedBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {
        for (int i = 1; i <= count; i++) {
            buffer.consume();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer();

        Producer producer = new Producer(buffer,5);
        Consumer consumer = new Consumer(buffer, 5);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items produced and consumed.");
    }
}


