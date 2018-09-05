package com.rs.app;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample4 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/* Wait for all futures completed */
		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(Sample4::getWishMessgae).exceptionally(Sample4::handleError);
		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(Sample4::getWishMessgae).exceptionally(Sample4::handleError);
		CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(Sample4::getWishMessgae).exceptionally(Sample4::handleError);
		CompletableFuture<Void> f = CompletableFuture.allOf(cf1, cf2, cf3);
		f.get(); //Here we are waiting for combined future to be completed.
		System.out.println(Stream.of(cf1, cf2,cf3).map(CompletableFuture::join).collect(Collectors.joining(" ")));
		System.out.println(cf1.isDone());
		System.out.println(cf2.isDone());
		System.out.println(cf3.isDone());
		System.out.println("main..");
	}

	static String getWishMessgae() {
		List<String> messages = Arrays.asList("Hello", "Hai", "Good Morning", "Good Evening", "Good Night");
		Random random = new Random();
		sleep(5000);
		int radomVal = random.nextInt(messages.size());
		if(radomVal == 0)
			throw new RuntimeException("Ho...,No");
		return messages.get(radomVal);
	}
	static String handleError(Throwable th) {
		return th.getMessage();
	}
	private static void sleep(long millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
