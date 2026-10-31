import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;
public class runner implements Runnable{

    private int taskID;

    public runner(int taskid) {this.taskID = taskid;}
    @Override
    public void run() {

        System.out.println("Task " + taskID + " is started by " + Thread.currentThread().getName());

        try{

            Thread.sleep(2000);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Task " + taskID + " is Completed by " + Thread.currentThread().getName());
    }
}
class task {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            executor.submit(new runner(i));
        }
        executor.shutdown();
    }
}
