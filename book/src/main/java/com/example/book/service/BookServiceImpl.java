package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.dao.BookDao;
import com.example.book.dto.Board;
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

	@Override
	public List<Board> getLists_board(String searchKey, String searchValue, int start, int end) {
		return bookDao.getLists_board(searchKey, searchValue, start, end);
	}

	@Override
	public int getDataCount_board(String searchKey, String searchValue) {
		return bookDao.getDataCount_board(searchKey, searchValue);
	}

	@Override
	public Board getReadData_board(int num) {
		return bookDao.getReadData_board(num);
	}

	@Override
	public void insertData_board(Board board) {
		bookDao.insertData_board(board);
		
	}

	@Override
	public int maxNum_board() {
		return bookDao.maxNum_board();
	}

	@Override
	public void updateData_board(Board board) {
		bookDao.updateData_board(board);
	}

	@Override
	public void deleteData_board(int num) {
		bookDao.deleteData_board(num);
		
	}
 
}
