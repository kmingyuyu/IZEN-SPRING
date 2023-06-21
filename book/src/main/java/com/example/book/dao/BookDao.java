package com.example.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.book.dto.Book;

@Mapper
public interface BookDao {
	public List<Book> getLists();
	
	
}
