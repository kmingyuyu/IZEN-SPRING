package com.shopmax.Exception;



public class OutOfStockException extends RuntimeException {
	
//	상품 주문 수령보다 재고가 적으면 발생되는 Exception
	public OutOfStockException(String message) {
		
		super(message);
	}
	
	
}
