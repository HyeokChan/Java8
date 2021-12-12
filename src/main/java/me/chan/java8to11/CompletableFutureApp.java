package me.chan.java8to11;

public class CompletableFutureApp {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
        // 스레드가 끝날때까지 기다린다.
        thread.join();
        System.out.println(thread + "is finished");

        

    }
}
