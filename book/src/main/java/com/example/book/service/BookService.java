package com.example.book.service;

import java.util.List;

import com.example.book.dto.Book;

public interface BookService {
	
	public List<Book> getLists(String searchKey , String searchValue , int start , int end);
	
	public int getDataCount(String searchKey , String searchValue);
	
	public Book getReadData(int num);
}
