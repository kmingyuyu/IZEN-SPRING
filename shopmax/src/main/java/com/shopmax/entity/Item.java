package com.shopmax.entity;

import java.time.LocalDateTime;

import com.shopmax.constant.ItemSellStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity //엔티티 클래스로 정의 (필수)
@Table(name="item") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Item {
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
	@Column(nullable = false)
	private String itemDetail; // 상품상세설명
	
	@Enumerated(EnumType.STRING) //enum의 이름을 db에 저장
	private ItemSellStatus itemSellStatus;// 판매상태 : SELL 혹은 SOLD OUT
	
	private LocalDateTime regTime; //등록시간
	
	private LocalDateTime updateTime; //수정시간
	
	
	
	
}
