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
	public List<Book> getLists(String searchKey, String searchValue, int start, int end) {
		return bookDao.getLists(searchKey, searchValue, start, end);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) {
		return bookDao.getDataCount(searchKey, searchValue);
	}

	@Override
	public Book getReadData(int num) {
		return bookDao.getReadData(num);
	}
 
}
