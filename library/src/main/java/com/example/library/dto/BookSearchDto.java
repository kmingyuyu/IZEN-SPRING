package com.example.library.dto;

import com.example.library.constant.StockOk;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchDto {
	
	private String searchDateType;
	private StockOk stockOk;
	private String searchBy;
	private String searchQuery = "";
	
}
