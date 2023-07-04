package com.shopmax.entity;

import groovy.transform.ToString;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Setter
@Table(name="")
public class CartItem {
	
	@Id
	@Column(name="cart_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int count;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	
}
