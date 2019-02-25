// Schedule task using schedule(), scheduleAtFixedRate() and scheduleAtFixedDelay()
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ExecuterServiceSchedulingTasks1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService
                .schedule(new Runnable() {
                              @Override
                              public void run() {
                                  System.out.println("Task executed after 1 second");
                              }
                          },
                        1,
                        TimeUnit.SECONDS);

        executorService.shutdown();

    }
}

class ExecuterServiceSchedulingTasks2 {

    public static void main(String[] args){

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


         executorService
                .scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000L);
                            System.out.println("ScheduleWithFixedDelay Scheduled Task to executed after fixed interval");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
                        ,
                        0,
                        1,
                        TimeUnit.SECONDS);

        executorService
                .scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000L);
                            System.out.println("ScheduleAtFixedRate Scheduled Task to executed after fixed interval");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ,
                        0,
                        1,
                        TimeUnit.SECONDS);



    }
}
