package com.example.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.book.dto.Book;

@Mapper
public interface BookDao {
	
	public List<Book> getLists(String searchKey , String searchValue , int start , int end);
	
	public int getDataCount(String searchKey , String searchValue);
	
	public Book getReadData(int num);
	
	
}
