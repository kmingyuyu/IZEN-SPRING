package com.example.book.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.book.dto.Book;
import com.example.book.service.BookService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	
//	최상단
	@RequestMapping(value = "/")
	public String mainPage() {
		return "mainPage";
	}
	
	@RequestMapping(value="/adminMain" , method= {RequestMethod.GET , RequestMethod.POST})
	public String list(HttpServletRequest request , Model model) {
//			
		List<Book> lists = bookService.getLists();
		
		model.addAttribute("lists",lists);
		
		return "content/admin/adminMain";
		
//		try {
//			String pageNum=request.getParameter("pageNum");
//			
//			int currentPage = 1;
//			
//			if(pageNum != null) {
//				currentPage = Integer.parseInt(pageNum);
//			}
//			
//			String searchKey = request.getParameter("searchKey");
//			String searchValue = request.getParameter("searchValue");
//			
//			if(searchValue == null) {
//				searchKey = ""; // 검색 키워드 디폴트 : ??
//				searchValue = "";
//			}else {
//				if(request.getMethod().equalsIgnoreCase("GET")) {
//					searchValue = URLDecoder.decode(searchValue , "UTF-8");
//				}
//			
//			}
//			
//			int dataCount = bookService.
//			
//			
//		}
//			catch (Exception e) {
//		}
//		
//		return null;
		}
		

		
		
		
		
		
		
		
	
	
	
}
