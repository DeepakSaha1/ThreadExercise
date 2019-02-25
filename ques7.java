// Submit List of tasks to ExecutorService and wait for the completion of all the tasks.
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class CallableFutureList implements Callable<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);

        //submit the task for execution
        List<Future> allFutures = new ArrayList<>();
        for(int i=0; i<15; i++) {
          Future<Integer> future =  service.submit(new CallableFutureDemo());
            allFutures.add(future);
        }

        for(int i=0; i <15; i++) {
            Future<Integer> fu = allFutures.get(i);
            Integer result = fu.get();
            System.out.println(result);
        }
        
        service.shutdown();
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        return new Random().nextInt();
    }
}
