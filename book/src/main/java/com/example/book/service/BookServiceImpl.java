package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.dao.BookDao;
import com.example.book.dto.Book;

@Service
public class BookServiceImpl implements BookService  {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<Book> getLists() {
		return bookDao.getLists();
	}
 
}
