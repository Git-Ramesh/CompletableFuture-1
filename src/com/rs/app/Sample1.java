package com.rs.app;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Sample1 {
	static int generate() {
		System.out.println("doing work..");
		try {
			Thread.sleep(2000);
		} catch(InterruptedException ie) {
			
		}
		return 2;
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// CompletableFuture.runAsync(() -> System.out.println("do work..."));
		/*CompletableFuture.supplyAsync(() -> {
			System.out.println("Doing work..");
			return 2;
		});*/
//		System.out.println(CompletableFuture.supplyAsync(Sample1::generate).get()); //blocking operation
		System.out.println(CompletableFuture.supplyAsync(Sample1::generate).getNow(-1)); 
		System.out.println("In main");
	}
}
