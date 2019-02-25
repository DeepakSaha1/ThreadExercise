// Increase concurrency with Thread pools using newCachedThreadPool() and newFixedThreadPool().

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Process implements Runnable{
    int id;

    public Process(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Thread name::"+Thread.currentThread().getName()+" Start :"+id);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread name::"+Thread.currentThread().getName()+" End :"+id);
    }
}

class ExecuterServiceDemo {
    public static void main(String[] args) {
      //comment one another to see the changes
       ExecutorService executorService= Executors.newFixedThreadPool(3);
        // ExecutorService executorService= Executors.newCachedThreadPool();

        for (int i = 0; i <= 10; i++) {
            executorService.submit(new Process(i));
        }
        executorService.shutdown();
    }
}
