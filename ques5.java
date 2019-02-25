import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class IsShutDownAndisTerminated{

        public static void main(String[] args) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            try {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Thread 1");
                    }
                });
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Thread 2");
                    }
                });
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000L);
                            System.out.println("Thread 3");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
            } finally {
//                executorService.shutdownNow();
                executorService.shutdown();
            }
            // isShutdown() : Returns true when executer service is shutdown.
            System.out.println(executorService.isShutdown());
            // isTerminated() : Returns true when all active tasks are completed.
            System.out.println(executorService.isTerminated());
            System.out.println("End");
        }
    }
