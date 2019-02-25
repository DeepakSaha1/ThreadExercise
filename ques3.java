
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecutorsDemo {
// Use a singleThreadExecutor to submit multiple threads.
        public static void main(String arg[]) {
            ExecutorService executorservice = Executors.newSingleThreadExecutor();

            Runnable task1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("My Task1 ..");

                }
            };

            Runnable task2 = () -> {
                System.out.println(Thread.currentThread().getName());
                System.out.println("My Task2 ..");
            };

            executorservice.submit(task1);
            executorservice.submit(task2);

            executorservice.shutdown();
        }
    }
