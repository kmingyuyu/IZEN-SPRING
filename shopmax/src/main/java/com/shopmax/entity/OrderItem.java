package com.shopmax.entity;


import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
@Entity
@Table(name="order_item")
public class OrderItem extends BaseEntity {
	
	@Id
	@Column(name="order_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int orderPrice; //주문가격
	
	private int count; //수량
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	
	
	
}
