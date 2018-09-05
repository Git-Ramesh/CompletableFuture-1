package com.rs.app;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Sample2 {
	static int generate() {
		System.out.println("generate: " + Thread.currentThread().getName());
		sleep(2000);
		return 2;
	}

	static void sleep(long millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static void printIt(int num) {
		System.out.println(num);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture.supplyAsync(Sample2::generate).thenApply(data -> data * 2).thenAccept(Sample2::printIt).get();
		
//		try {
//			System.out.println(completableFuture.get());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}

		System.out.println("main..");
	}
}
