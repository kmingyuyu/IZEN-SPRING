package com.shopmax.entity;


import com.shopmax.Exception.OutOfStockException;
import com.shopmax.constant.ItemSellStatus;
import com.shopmax.dto.ItemFormDto;

import jakarta.persistence.*;
import lombok.*;

@Entity //엔티티 클래스로 정의 (필수)
@Table(name="item") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Item extends BaseEntity {
//	jpa는 db 데이터입력시 객체의 이름에서 대문자를 인식해서 들어간다 itemNm -> db컬럼명 : item_nm
	
	@Id
	@Column(name="item_id") //테이블로 생설될때 컬럼이름을 지정 해준다.
	@GeneratedValue(strategy = GenerationType.AUTO) //필드의 값을 자동으로 생성해주는 전략(strategy) 사용
	private Long id; // 상품 코드
	
	@Column(nullable = false , length = 50) // not null 여부 , 컬럼의 크기 지정
	private String itemNm; //상품평
	
	@Column(nullable = false)
	private Integer price; // 가격
	
	@Column(nullable = false)
	private Integer stockNumber; // 재고수량 
	
	@Lob //clob과 같은 큰타입의 문자타입으로 컬럼을 만듦
	@Column(nullable = false , columnDefinition = "Longtext")
	private String itemDetail; // 상품상세설명
	
	@Enumerated(EnumType.STRING) //enum의 이름을 db에 저장
	private ItemSellStatus itemSellStatus;// 판매상태 : SELL 혹은 SOLD OUT
	
//	item 엔티티 수정
	public void updateItem(ItemFormDto itemFormDto) {
		
		this.itemNm = itemFormDto.getItemNm();
		this.price = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
		
	}
	
//	재고를 감소시킨다.
	public void removeStock(int stockNumber) {
		int restStock = this.stockNumber - stockNumber; // 남은 재고수량
		
		if(restStock < 0) {
			throw new OutOfStockException
			("상품의 재고가 부족합니다. 현재 재고수량" + this.stockNumber);
		}
		
		this.stockNumber = restStock; // 남은 재고수량 반영
		
		
	}
	
	
	
	
}
