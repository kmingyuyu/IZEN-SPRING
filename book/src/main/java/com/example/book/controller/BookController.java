package com.example.book.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.book.dto.Book;
import com.example.book.service.BookService;
import com.example.book.util.BookUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookUtil bookUtil;
	
	
//	최상단
	@RequestMapping(value = "/")
	public String mainPage() {
		return "mainPage";
	}
	
	@RequestMapping(value="/adminMain" , method= {RequestMethod.GET , RequestMethod.POST})
	public String list(HttpServletRequest request , Model model , Book book) {
//			
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
			
			int dataCount = bookService.getDataCount(searchKey, searchValue);
			
			int numPerPage = 5 ;
			int totalPage = bookUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage > totalPage)	currentPage = totalPage	;
			

			int start = (currentPage - 1) * numPerPage + 1 ; // 1 6 11 16 ...
			int end = currentPage * numPerPage; // 5 10 15 20....
			
			List<Book> lists = bookService.getLists(searchKey, searchValue, start, end);
			
			String param = "";
			
			if(searchValue != null && !searchValue.equals("")) {
//				searchValue에 검색어가 있다면
				param = "searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue , "UTF-8"); //컴퓨터의 언어로 인코딩
			}
			
			String listUrl = "/adminMain";
			
			if(!param.equals("")) listUrl += "?" + param;
			
			String pageIndexList = bookUtil.pageIndexList(currentPage, totalPage, listUrl);	
			
			String articleUrl = "/adminArticle?pageNum=" + currentPage ;
			
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
		
		return "content/admin/adminMain";
		}
		
		@RequestMapping(value="/adminArticle" , method = {RequestMethod.GET,RequestMethod.POST})
		public String article (HttpServletRequest request , Model model) {
			
			try {
				int num = Integer.parseInt(request.getParameter("num"));
				String pageNum = request.getParameter("pageNum");
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				
				if(searchValue != null) {
					searchValue = URLDecoder.decode(searchValue , "UTF-8");
				}
				
				Book book = bookService.getReadData(num);
				
				if(book == null) {
					return "redirect:/adminMain?pageNum=" + pageNum;
				}
				
				String param = "pageNum=" + pageNum;
				
				if(searchValue != null && !searchValue.equals("")) {
//					searchValue에 검색어가 있다면
					param += "&searchKey=" + searchKey;
					param += "&searchValue=" + URLEncoder.encode(searchValue , "UTF-8"); //컴퓨터의 언어로 인코딩
				}
				
				model.addAttribute("book" , book);
				model.addAttribute("params" , param);
				model.addAttribute("pageNum" , pageNum);
				
			} catch (Exception e) {
			}
			
			return "content/admin/adminArticle";
		}
		
		
		
		
		
		
	
	
	
}
