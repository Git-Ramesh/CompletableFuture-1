package com.rs.app;

import java.util.concurrent.*;

public class CompletableFutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 0;

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "custom-executor-" + count);
            }
        });
        /*
        //If CompletableFuture has data then isDone returns true
        CompletableFuture future = new CompletableFuture();
        future.complete("Data");
        System.out.println("Is Done: " + future.isDone());*/

       /* //If runnable runnable task given to runAsync completed the isDone returns true
        //If you don't provide any Executor then it by default uses the ForkJoinPool, which uses
        //daemon threads to execute runnable task.
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            System.out.println("Executing runnable task..");
            System.out.println(Thread.currentThread().getId());
            System.out.println(Thread.currentThread().isDaemon()); // true

        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println(future.isDone());*/
        /*
        //Take the CompletableFuture completed data and then pass to a Function
        CompletableFuture future = CompletableFuture
                .completedFuture("Data")
                .thenApply(data -> data.toUpperCase());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        }*/
       /* CompletableFuture<String> future = CompletableFuture
                .completedFuture("Data")
                .thenApplyAsync(s -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()); //custom-executor-0
                    System.out.println(Thread.currentThread().isDaemon()); //false

                    return s.toUpperCase();
                }, executorService);
        //Returns the result value when complete, or throws an (unchecked) exception if completed exceptionally
        System.out.println("my message");
        try {
            System.out.println(future.get()); //DATA
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(future.join()); //DATA
*/
     /*  //Asynchronously Consuming the Result of the Previous Stage
        StringBuilder sb = new StringBuilder();
       CompletableFuture future = CompletableFuture.completedFuture("Data")
               .thenAcceptAsync(result -> sb.append(result));
        System.out.println(future.join());
        System.out.println(sb);
    */
      /*  //Completing a Computation Exceptionally
        CompletableFuture future = CompletableFuture.completedFuture("Data")
                .thenApplyAsync(data -> {
                    //throw new RuntimeException("Me..");
                    return data.toUpperCase();
                });
        CompletableFuture cf =future.handle((o, th) -> {
            System.out.println(o);//DATA
            System.out.println(th);
            return (th != null) ? "Cancled" : o;
        });
        try {
            System.out.println(future.join());
        } catch (CompletionException e) {
            System.out.println("***"+e.getMessage());
        }
        System.out.println("---"+cf.join());*/
     /* //Canceling a Computation
        CompletableFuture future = CompletableFuture.completedFuture("Data")
                .thenApplyAsync(data -> {
                    //throw new RuntimeException("Me..");
                    return data.toUpperCase();
                });
        CompletableFuture cf = future.exceptionally(throwable -> "Completed exceptionally");
        if(future.isCompletedExceptionally()) {
            System.out.println(cf.join()); //Completed exceptionally
        } else {
            System.out.println(future.join()); //DATA
            System.out.println(cf.join()); //DATA
        }*/
     //Applying a Function to the Result of Either of Two Completed Stages

        CompletableFuture future = CompletableFuture.completedFuture("Data")
                .thenApplyAsync(String::toUpperCase);
        CompletableFuture cf = future.applyToEither(CompletableFuture.completedFuture("Data1").thenApplyAsync(data->data+"***"), data->data+"sajh");
        System.out.println(cf.join());
    }
}
