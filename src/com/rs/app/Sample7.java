package com.rs.app;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Sample7 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		// Function<T, R>
		// BiFunction<T, U, R>
		CompletableFuture<CompletableFuture<List<String>>> completableFuture = CompletableFuture
				.supplyAsync(() -> getNames(), es);
		completableFuture
				.thenCompose(cf -> CompletableFuture
						.completedFuture(cf.join().stream().map(String::toUpperCase).collect(Collectors.joining(" "))))
				.handle((s, th) -> s != null ? s : th.getMessage()).thenAccept(System.out::println);
		es.shutdown();
		System.out.println(es.awaitTermination(100, TimeUnit.MILLISECONDS));
		System.out.println("main...");
	}

	static void sleep(long millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static String handleError(Throwable th) {
		return th.getMessage();
	}

	static CompletableFuture<List<String>> getNames() {
		List<String> namesList = Arrays.asList("Ramesh", "Jag", "Foo");
		sleep(5000);
		if (true)
			throw new IllegalStateException("Ho..no!");
		return CompletableFuture.completedFuture(namesList);
	}
}
