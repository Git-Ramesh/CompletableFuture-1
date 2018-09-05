package com.rs.app;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Sample5 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();

		CompletableFuture<String> cf = CompletableFuture.supplyAsync(Sample5::getWishMessgae, pool);
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.SECONDS);
		cf.thenAccept(System.out::println);
		System.out.println("main...");
	}

	static String getWishMessgae() {
		List<String> messages = Arrays.asList("Hello", "Hai", "Good Morning", "Good Evening", "Good Night");
		Random random = new Random();
		sleep(5000);
		int radomVal = random.nextInt(messages.size());
		return messages.get(radomVal);
	}

	private static void sleep(long millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
