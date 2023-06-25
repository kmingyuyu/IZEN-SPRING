package com.example.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	private int num; //번호
	private String pwd; //비밀번호
	private String email; //이메일
	private String m_text; //본문
	private String reg_date; //작성일
}
