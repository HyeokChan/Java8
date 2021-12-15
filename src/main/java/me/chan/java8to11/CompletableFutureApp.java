package me.chan.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
        // 스레드가 끝날때까지 기다린다.
        thread.join();
        System.out.println(thread + "is finished");

        // Executors
        System.out.println("Executors >>");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            System.out.println("Thread " + Thread.currentThread().getName());
        });
        executorService.shutdown();

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.submit(getRunnable("Hello"));
        executorService1.submit(getRunnable("Keesun"));
        executorService1.submit(getRunnable("Chan"));
        executorService1.submit(getRunnable("JAVA"));
        executorService1.submit(getRunnable("Thread"));
        executorService1.shutdown();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(getRunnable("HelloWorld"), 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();

        ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService1.scheduleAtFixedRate(getRunnable("Period"), 3, 2, TimeUnit.SECONDS);
        scheduledExecutorService1.shutdown(); // Period 확인하려면 주석처리

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "JAVA";
        };

        Callable<String> chan = () -> {
            Thread.sleep(1000L);
            return "CHAN";
        };

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        Future<String> helloFuture = executorService2.submit(hello);
        System.out.println("helloFuture.isDone() : " + helloFuture.isDone());
        System.out.println("Started!");
        helloFuture.get();
        // 작업 취소
        //helloFuture.cancel(true);
        System.out.println("helloFuture.isDone() : " + helloFuture.isDone());
        System.out.println("End!");
        executorService2.shutdown();

        ExecutorService executorService3 = Executors.newFixedThreadPool(4);
        // invokeAll 모든 Callable이 끝날때 까지 기다린다.
        List<Future<String>> futures = executorService3.invokeAll(Arrays.asList(hello, java, chan));
        for (Future<String> future : futures) {
            System.out.println("invokeAll" + future.get());
        }
        String s = executorService3.invokeAny(Arrays.asList(hello, java, chan));
        System.out.println("invokeAny" + s);

    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " : " + Thread.currentThread().getName());
    }
}
