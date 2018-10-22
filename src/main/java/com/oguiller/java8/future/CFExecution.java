package com.oguiller.java8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFExecution {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        long time = System.currentTimeMillis();

        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> delayedCallback("Hello"));

//        future.thenAccept((message) -> delayedCallback(message+"1")); // Uses the same thread as the previous compleatableFuture
//        future.thenAcceptAsync((message) -> delayedCallback(message+"1"));
        future.thenAcceptAsync((message) -> delayedCallback(message + "1"), executorService);

        System.out.println("Hey from " + Thread.currentThread().getName());

//        future.thenAcceptAsync((message) -> delayedCallback(message+"2"));
        future.thenAcceptAsync((message) -> delayedCallback(message + "2"), executorService);

        Thread.sleep(7000);
    }

    public static String delayedCallback(String message) {
        uncheckedSleep(2000); // Comment this with Accept without a suffix to find out that commonPool is not used by main. It could block the main thread.
        System.out.println(message + " from " + Thread.currentThread().getName());
        return message;
    }

    public static void uncheckedSleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}