package me.chan.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
            System.out.println("invokeAll : " + future.get());
        }
        String s = executorService3.invokeAny(Arrays.asList(hello, java, chan));
        System.out.println("invokeAny : " + s);
        executorService3.shutdown();

        // CompletableFuture 1
        System.out.println("CompletableFuture >> ");
        CompletableFuture<String> objectCompletableFuture = new CompletableFuture<>();
        objectCompletableFuture.complete("chan");
        System.out.println("objectCompletableFuture.get() : " + objectCompletableFuture.get());


        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        completableFuture.get();

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println("stringCompletableFuture.get() : " + stringCompletableFuture.get());

        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((string) -> {
            System.out.println("thenApply " + Thread.currentThread().getName());
            return string.toUpperCase();
        });
        System.out.println("stringCompletableFuture1.get() : " + stringCompletableFuture1.get());

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((string1) -> {
            System.out.println("thenAccept " + Thread.currentThread().getName());
            System.out.println(string1.toUpperCase());
        });
        voidCompletableFuture.get();

        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println("thenRun " + Thread.currentThread().getName());
        });
        voidCompletableFuture1.get();

        // CompletableFuture 2 (조합, 예외처리)
        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> objectCompletableFuture1 = stringCompletableFuture2.thenCompose((string3) -> {
            return getStringCompletableFuture3(string3);
        });
        System.out.println("objectCompletableFuture1.get() : " + objectCompletableFuture1.get());

        CompletableFuture<String> helloCf = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> worldCf = CompletableFuture.supplyAsync(() -> {
            System.out.println("World" + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<String> stringCompletableFuture3 = helloCf.thenCombine(worldCf, (h, w) -> {
            return h + " " + w;
        });
        System.out.println("stringCompletableFuture3.get() : " + stringCompletableFuture3.get());

        List<CompletableFuture> futures1 = Arrays.asList(helloCf, worldCf);
        CompletableFuture[] completableFutures = futures1.toArray(new CompletableFuture[futures1.size()]);
        CompletableFuture<List<Object>> listCompletableFuture = CompletableFuture.allOf(completableFutures)
                .thenApply(v -> {
                    return futures1.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList());
                });
        listCompletableFuture.get().forEach(System.out::println);

        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.anyOf(helloCf, worldCf).thenAccept(System.out::println);
        System.out.println("voidCompletableFuture2 = " + voidCompletableFuture2.get());

        // 예외처리
        boolean throwError = true;
        CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello2 = " + Thread.currentThread().getName());
            return "Hello2";
        }).exceptionally(ex -> {
            System.out.println("ex = " + ex);
            return "Error!";
        });
        System.out.println("hello2 = " + hello2.get());

        // handle
        CompletableFuture<String> hello3 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello3 = " + Thread.currentThread().getName());
            return "Hello3";
        }).handle((result, ex) -> {
            if(ex != null){
                System.out.println("ex = " + ex);
                return "Error3!";
            }
            return result;
        });
        System.out.println("hello3 = " + hello3.get());






    }
    private static CompletableFuture<String> getStringCompletableFuture3(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + Thread.currentThread().getName());
            return message + "World";
        });
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " : " + Thread.currentThread().getName());
    }
}
