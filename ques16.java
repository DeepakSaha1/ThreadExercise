import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

interface Task {
	public void performTask();
}

class Worker implements Runnable {
	private Task task;
	public Worker(Task task) {
		this.task = task;
	}
	@Override
	public void run() {
		task.performTask();
	}
}

class Main {
	public static void main(String[] args) {
		final int threadCount = 5;
		final ExecutorService service = Executors.newFixedThreadPool(threadCount);
    // tryLock(long timeout, TimeUnit unit): We can give a waiting time to tryLock()
    // so that it must wait to acquire lock for the given time if lock is not available. 
    // Suppose resource is locked by any thread and current thread calls tryLock(100, TimeUnit.MILLISECONDS)
    // then current thread will wait max for 100 milliseconds to acquire lock and once other thread which
    // already has lock, unlocks it within 100 milliseconds, the current thread will acquire lock and hold
    // count will be incremented by one, even if other threads are waiting for lock. If for the specified time,
    // current thread is not able to acquire lock, this method will return false.
		final Task task = new TryLockDemo();
		for (int i=0; i< threadCount; i++) {
			service.execute(new Worker(task));
		}
		service.shutdown();
	}
}

class TryLockDemo implements Task {
	final ReentrantLock reentrantLock = new ReentrantLock();
	@Override
	public void performTask() {
		try {
			boolean flag = reentrantLock.tryLock(100, TimeUnit.MILLISECONDS);
			if (flag) {
			    try {
					System.out.println(Thread.currentThread().getName() +": Lock acquired.");
					System.out.println("Performing task...");
			    } finally {
					System.out.println(Thread.currentThread().getName() +": Lock released.");
					reentrantLock.unlock();
			    }
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
