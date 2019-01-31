package com.stream;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * CompletableFuture是Java8新增的一个超大型工具类,为什么说她大呢?因为一方面它实现了Future接口,更重要的是,它实现了CompletionStage接口.这个接口也是Java8新增加的,而CompletionStage拥有多达约40种方法,
 
    * 完成了通知我
    * 异步执行任务
 * @author Administrator
 *
 */
public class StreamTest {
	
	
	
	private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
	        new Shop(":LetsSaveBig"),
	        new Shop("MyFavoriteShop"),
	        new Shop("BuyItAll"));
	
	private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testName() throws Exception {
		
		//CompletableFuture.supplyAsync()
		//方法构造了一个CompletableFuture实例,在supplyAsync()函数中,
		///他会在一个新的线程中,执行传入的参数.在这里,,他会执行calc()方法,
		//而calc()方法的执行可能是比较慢的,但是不影响CompletableFuture实例的构造速度,因此supplyAsync()会立即返回,
		//他返回的CompletableFuture对象实例就可以作为这次调用的契约,在将来任何场合,用于获得最终的计算结果
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> cale(50));
		
		
		System.out.println("========");
		
	    System.out.println(future.get());
	    
	    System.out.println("========");
	}
	
	
	public static Integer cale(Integer para) {
	    try {
	        Thread.sleep(1000);
	 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    return para * para;
	}
	
	
	
	@Test
	public void testName1() throws Exception {
		CompletableFuture<Void> future = CompletableFuture
		        .supplyAsync(() -> cale(50))
		        .thenApply(i -> Integer.toString(i))
		        .thenApply(str -> "\"" + str + "\"")
		        .thenAccept(System.out::println);
		future.get();
	}

	
	
	public static List<String> asyncFindprices(String product) {
	    //使用这种方式,你会得到一个List<CompletableFuture<String>>,列表中的每一个CompletableFuture对象在计算完成后都包含商店的String类型的名称.
	    //但是,由于你用CompletableFuture实现了asyncFindprices方法要求返回一个List<String>.你需要等待所有的future执行完毕,将其包含的值抽取出来,填充到列表中才能返回
	    List<CompletableFuture<String>> priceFuture = shops
	            .stream()
	            .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
	            .collect(Collectors.toList());
	    //为了实现这个效果,我门可以向最初的List<CompletableFuture<String>>施加第二个map操作,对list中的每一个future对象执行join操作,一个接一个地等待他们允许结束,join和get方法
	    //有相同的含义,不同的在于join不会抛出任何检测到的异常
	    return priceFuture
	            .stream()
	            .map(CompletableFuture::join)
	            .collect(Collectors.toList());
	}
	
	
/*	public static List<String> findPrices(String product) {
	    List<CompletableFuture<String>> collect = shops
	            .stream()
	            //以异步凡是取得每个shop中指定产品的原始价格
	            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
	            //Quote对象存在时,对其返回值进行转换
	            .map(future -> future.thenApply(Quote::parse))
	            //使用另一个异步任务构建期望的Future,申请折扣 thenCompose 将多个future组合 一个一个执行
	            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
	            .collect(Collectors.toList());
	    return collect
	            .stream()
	            //等待流中所有的future执行完毕,并提取各自的返回值
	            .map(CompletableFuture::join)
	            .collect(Collectors.toList());
	}*/
	
	
	public static List<String> asyncFindpricesThread(String product) {
	    List<CompletableFuture<String>> priceFuture = shops
	            .stream()
	            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor))
	            .collect(Collectors.toList());
	    return priceFuture
	            .stream()
	            .map(CompletableFuture::join)
	            .collect(Collectors.toList());
	}
}
