package com.example.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.book.dto.Board;


@Mapper
public interface BoardDao {
	public List<Board> getLists_board(String searchKey , String searchValue , int start , int end);
	
	public int getDataCount_board(String searchKey , String searchValue);
	
	public Board getReadData_board(int num);
	
	public void insertData_board(Board board);
	
	public int maxNum_board();
	
}
