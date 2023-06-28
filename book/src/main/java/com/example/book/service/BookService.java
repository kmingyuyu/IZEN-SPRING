package com.example.book.service;

import java.util.List;

import com.example.book.dto.Board;
import com.example.book.dto.Book;

public interface BookService {
	
//	book
	
	public List<Book> getLists(String searchKey , String searchValue , int start , int end);
	
	public int getDataCount(String searchKey , String searchValue);
	
	public Book getReadData(int num);
	
	
//	board
	
	public List<Board> getLists_board(String searchKey , String searchValue , int start , int end);
	
	public int getDataCount_board(String searchKey , String searchValue);
	
	public Board getReadData_board(int num);
	
	public void insertData_board(Board board);
	
	public int maxNum_board();
	
	public void updateData_board(Board board);
	
	public void deleteData_board(int num);
	
}
