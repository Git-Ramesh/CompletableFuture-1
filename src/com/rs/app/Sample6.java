package com.rs.app;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class Sample6 {
	public static void main(String[] args) {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
				.thenCompose(mesg -> CompletableFuture.supplyAsync(() -> mesg + " World"));
		System.out.println(completableFuture.join());

		/*
		 * If you want to execute two independent Futures and do something with their
		 * results, use the thenCombine method that accepts a Future and a Function with
		 * two arguments to process both results
		 */
		// BiFunction<T, U, R>
		CompletableFuture<String> cf1 = CompletableFuture.completedFuture("Hello");
		CompletableFuture<String> cf2 = CompletableFuture.completedFuture(" World");
		CompletableFuture<String> combinedFuture = cf1.thenCombine(cf2, (msg1, msg2) -> msg1 + msg2);
		combinedFuture.thenAccept(System.out::println);
	}
}
