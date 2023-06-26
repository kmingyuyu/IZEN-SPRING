package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.dao.BoardDao;
import com.example.book.dto.Board;

@Service
public class BoardServiceImpl implements BoardService  {
	
	@Autowired
	private BoardDao boardDao ;

	@Override
	public List<Board> getLists_board(String searchKey, String searchValue, int start, int end) {
		return boardDao.getLists_board(searchKey, searchValue, start, end);
	}

	@Override
	public int getDataCount_board(String searchKey, String searchValue) {
		return boardDao.getDataCount_board(searchKey, searchValue);
	}

	@Override
	public Board getReadData_board(int num) {
		return boardDao.getReadData_board(num);
	}

	@Override
	public void insertData_board(Board board) {
		boardDao.insertData_board(board);
		
	}

	@Override
	public int maxNum_board() {
		return boardDao.maxNum_board();
	}

}
