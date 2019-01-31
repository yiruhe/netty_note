package com.stream;

public class Shop {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Shop(String name) {
		super();
		this.name = name;
	}
	
	public double getPrice(String product) {
	    return calculatePrice(product);
	}

	/**
	 * 同步计算商品价格的方法
	 *
	 * @param product 商品名称
	 * @return 价格
	 */
	private double calculatePrice(String product) {
	  
	    return  product.charAt(0) + product.charAt(1);
	}

}
