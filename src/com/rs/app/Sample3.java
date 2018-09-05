package com.rs.app;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import com.rs.app.model.Employee;

public class Sample3 {
	public static CompletableFuture<List<Employee>> getEmployees() {
		System.out.println("getEmployees: " + Thread.currentThread());
		Employee e1 = new Employee(1, "Ramesh");
		Employee e2 = new Employee(1, "Jag");
		Employee e3 = new Employee(1, "Mark");
		Employee e4 = new Employee(1, "Jhan");

		List<Employee> employees = Arrays.asList(e1, e2, e3, e4);
		sleep(5000);
		return CompletableFuture.completedFuture(employees);
	}

	static void sleep(long millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		System.out.println(Thread.currentThread());

		CompletableFuture<List<Employee>> fu = CompletableFuture.supplyAsync(Sample3::getEmployees, es)
				.thenCompose(cf -> cf.thenApply(emps -> emps));
		fu.thenApply(emps -> emps.stream().findFirst().get()).thenAccept(System.out::println);
		System.out.println("Print..");
		// future.thenAccept(System.out::println);
		System.out.println(Thread.currentThread());
	}
}
