import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class CallableFutureDemo implements Callable<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);

        //submit the task for execution
        Future<Integer> future;
        List<Future> allFutures = new ArrayList<>();
        for(int i=0; i<15; i++) {
            future =  service.submit(new CallableFutureDemo());
            allFutures.add(future);

//            will cancel the all task
            /*if(future.cancel(false)) {
                System.out.println("cancel() called..");
            }*/
        }



        for(int i=0; i <15; i++) {
            Future<Integer> fu = allFutures.get(i);
            Integer result = fu.get();
            System.out.println(result);

            if (fu.isDone()) {
                System.out.println("isDone() called.."); //blocking
            }

            //no effect of cancel()
            if(fu.cancel(false)) {
                System.out.println("cancel() called..");
            }
            if(fu.isCancelled()){
                System.out.println("isCancelled() called..");
            }
        }

        System.out.println("Thread Name: " + Thread.currentThread().getName());
        service.shutdown();
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        return new Random().nextInt();
    }
}
