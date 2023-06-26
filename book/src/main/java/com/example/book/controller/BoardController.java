package com.example.book.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.book.dto.Board;
import com.example.book.service.BoardService;
import com.example.book.util.BookUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BookUtil bookUtil;
	
	@RequestMapping(value = "/boardList" , method= {RequestMethod.GET , RequestMethod.POST})
	public String list(HttpServletRequest request , Model model , Board board) {
		
		try {
			String pageNum=request.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			}
			
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue == null) {
				searchKey = "writer"; // 검색 키워드 디폴트 : ??
				searchValue = "";
			}else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue , "UTF-8");
				}
			
			}
			
			int dataCount = boardService.getDataCount_board(searchKey, searchValue);
			
			int numPerPage = 5 ;
			int totalPage = bookUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage > totalPage)	currentPage = totalPage	;
			

			int start = (currentPage - 1) * numPerPage + 1 ; // 1 6 11 16 ...
			int end = currentPage * numPerPage; // 5 10 15 20....
			
			List<Board> lists = boardService.getLists_board(searchKey, searchValue, start, end);
			
			String param = "";
			
			if(searchValue != null && !searchValue.equals("")) {
//				searchValue에 검색어가 있다면
				param = "searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue , "UTF-8"); //컴퓨터의 언어로 인코딩
			}
			
			String listUrl = "/boardList";
			
			if(!param.equals("")) listUrl += "?" + param;
			
			String pageIndexList = bookUtil.pageIndexList(currentPage, totalPage, listUrl);	
			
			String articleUrl = "/boardArticle?pageNum=" + currentPage ;
			
			if(!param.equals("")) {
				articleUrl += "&" + param;
			}
			
			model.addAttribute("lists" , lists); //db에서 가져온 전체 게시물
			model.addAttribute("articleUrl" , articleUrl); //상세페이지로 이동하기 위한 url
			model.addAttribute("pageIndexList" , pageIndexList); //하단 버튼
			model.addAttribute("dataCount" , dataCount); //전체 게시물 갯수
			
			
		
			}
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "리스트를 블러오는 중 에러가 발생했습니다");
		}
		
		return "content/board/boardList";
		
	}
	
	@RequestMapping(value = "/boardCreated" , method= RequestMethod.GET) 
	public String created() {
		return "content/board/boardCreated";
	}
	
	@RequestMapping(value ="/boardCreated" , method=RequestMethod.POST)
	public String createdOK(Board board , HttpServletRequest request , Model model ) {
		
		try {
			int maxNum = boardService.maxNum_board();
			
			board.setNum(maxNum + 1); // nym컬럼에 들어갈 값을 1 증가시켜준다.
			
			boardService.insertData_board(board);
			model.addAttribute("clearMessage", "게시글 작성이 완료 되었습니다");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			 model.addAttribute("errorMessage", "게시글을 작성중 에러가 발생했습니다"); 
			 return "content/board/boardCreated";
			 
		}
		
		return "redirect:/";
	
	

	}
}
	