package com.example.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	private int num; // 일련번호
	private String subject; //제목
	private String writer; //글쓴이
	private String publisher; //출판사
	private String type; //종류
	private String img; //표지
	private String publication_date; //출간일
	private int inventory; //남은 재고
	private String story; //줄거리
}
