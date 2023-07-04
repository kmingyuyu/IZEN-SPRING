package com.example.library.entity;

import java.time.LocalDateTime;

import com.example.library.constant.StockOk;
import com.example.library.constant.TypeOk;

import groovy.transform.ToString;
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

@Getter
@Setter
@ToString
@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_id")
	private Long id;
	
	private String bookName; //책 제목
	
	private String writer; //작가
	
	private String publisher; //출판사
	
	@Enumerated(EnumType.STRING)
	private TypeOk typeOk; //책 분류
	
	
	private LocalDateTime pubDate; //출간일
	
	private String story; //간략 줄거리
	
	@Enumerated(EnumType.STRING)
	private StockOk stockOk;
	
	private int borrowCount; //총 대출수
	
}
