package com.stream;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class StreamTest2 {
	
	
	@Test
	public void test1() throws Exception {
		
		 ExecutorService executor = Executors.newFixedThreadPool(10);
	        Future<Double> future = executor.submit(new Callable<Double>() {
	            @Override
	            public Double call() throws Exception {
	            	
	            	System.out.println("++++++++++++++++++++++++++++++");
	                return doSomeLongComputation();
	            }
	        });
	        System.out.println("=======doSomeLongComputation========  start =====");
	        doSomethingElse();
	    	System.out.println("=======doSomeLongComputation========  end =====");
	        try {
	            //最多等待1秒
	            Double result = future.get(1,TimeUnit.SECONDS);
	        } catch (InterruptedException e) {
	            //当前线程等待过程中被打断
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            //计算时出现异常
	            e.printStackTrace();
	        } catch (TimeoutException e) {
	            //完成计算前就超时
	            e.printStackTrace();
	        }
		
	}

	private void doSomethingElse() {
		
		
		System.out.println("=======doSomethingElse=============");
		
	}

	protected Double doSomeLongComputation() {
		System.out.println("=======doSomeLongComputation=============");
		return 10.1;
	}

	
	
	
	@Test
	public void test2() throws Exception {
		
		System.out.println(Thread.currentThread().getId());
		
		System.out.println("======");
		
		CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
			System.out.println("==+++++++====");
			System.out.println(Thread.currentThread().getId());
		    try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		       throw new IllegalStateException(e);
		    }
		    return "Rajeev";
		}).thenApply(name -> {
			System.out.println(Thread.currentThread().getId());
		    return "Hello " + name;
		}).thenApplyAsync(greeting -> {
			System.out.println(Thread.currentThread().getId());
		    return greeting + ", Welcome to the CalliCoder Blog";
		});

		System.out.println(welcomeText.get());
	}
	
	
	
	
	
	@Test
	public void test3() throws Exception {
		System.out.println("Retrieving weight.");
		CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		       throw new IllegalStateException(e);
		    }
		    return 65.0;
		});

		System.out.println("Retrieving height.");
		CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		       throw new IllegalStateException(e);
		    }
		    return 177.8;
		});

		System.out.println("Calculating BMI.");
		CompletableFuture<Double> combinedFuture = weightInKgFuture
		        .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
		    Double heightInMeter = heightInCm/100;
		    return weightInKg/(heightInMeter*heightInMeter);
		});

		System.out.println("Your BMI is - " + combinedFuture.get());
	}
	
	
}
