class SimpleThread {

    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                    System.out.println("Running 1st Thread");
                    for(char c = 'a'; c<'j'; c++)
                      System.out.println(Thread.currentThread().getName()+":::"+c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                    System.out.println("Running 2nd Thread");
                    for(int i = 0; i<10; i++)
                      System.out.println(Thread.currentThread().getName()+":::"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Ended....");
    }
}
