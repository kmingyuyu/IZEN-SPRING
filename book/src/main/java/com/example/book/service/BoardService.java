package com.example.book.service;

import java.util.List;

import com.example.book.dto.Board;

public interface BoardService {
	public List<Board> getLists_board(String searchKey , String searchValue , int start , int end);
	
	public int getDataCount_board(String searchKey , String searchValue);
	
	public Board getReadData_board(int num);
	
	public void insertData_board(Board board);
	
	public int maxNum_board();
	
}
