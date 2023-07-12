package com.shopmax.dto;

//dto로 바로 데이터를 받고싶다면 dto를 인터페이스로 생성
public interface ItemRankDto {
	
	int getNum();
	
	long getId();
	
	String getItemNm();
	
	int getPrice();
	
	String getImgUrl();
	
	String getRepImgYn();
	
}
