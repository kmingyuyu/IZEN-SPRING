package com.shopmax.entity;


import java.time.LocalDateTime;

import com.shopmax.constant.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //엔티티 클래스로 정의 (필수)
@Table(name="Orders") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Orders {
	

	@Id
	@Column(name="order_id") //테이블로 생설될때 컬럼이름을 지정 해준다.
	@GeneratedValue(strategy = GenerationType.AUTO) //필드의 값을 자동으로 생성해주는 전략(strategy) 사용
	private Long id;
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	
	
}
