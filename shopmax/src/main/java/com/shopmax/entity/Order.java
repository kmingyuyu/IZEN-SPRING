package com.shopmax.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.shopmax.constant.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //엔티티 클래스로 정의 (필수)
@Table(name="orders") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Order {
	

	@Id
	@Column(name="order_id") //테이블로 생설될때 컬럼이름을 지정 해준다.
	@GeneratedValue(strategy = GenerationType.AUTO) //필드의 값을 자동으로 생성해주는 전략(strategy) 사용
	private Long id;
	
	private LocalDateTime orderDate; // 주문일
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus; //주문상태
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
//	order에서도 orderItem을 참조할수 있도록 양방향 관계를 만든다.
//	다만 orderItem은 자식 테이블이 되므로 List로 만든다
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.LAZY) 
	        // mappedBy:연관 관계의 주인 설정(외래키 지정)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	public void addOrderItem(OrderItem orderItem) {
		
		this.orderItems.add(orderItem);
		orderItem.setOrder(this); // !양방향 참조관계 일때는 orderItem객체에도 order객체를 세팅한다.
		
	}
	
//	order 객체를 생성 해준다.
	public static Order createOrder(Member member , List<OrderItem> orderItemList) {
		
		Order order = new Order();
		order.setMember(member);
		
		for(OrderItem orderItem : orderItemList) {
			order.addOrderItem(orderItem);
		}
		
		order.setOrderStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		
		return order;
		
	}
	
//	총 주문 금액
	public int getTotalPrice() {
		
		int totalPirce = 0;
		
		for(OrderItem orderItem : orderItems) {
			totalPirce += orderItem.getTotalPrice();
		}
		
		return totalPirce;
		
	}
	
	
	
	
}
