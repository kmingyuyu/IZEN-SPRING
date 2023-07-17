package com.example.library.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.library.dto.BookFormDto;
import com.example.library.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
//	상품 등록 페이지
	@GetMapping(value="/admin/book/new")
	public String bookForm(Model model) {
		
		model.addAttribute("bookFormDto" , new BookFormDto());
		
		return "book/bookForm";
	}
	
	@PostMapping(value="/admin/book/new")
	public String bookNew
	(@Valid BookFormDto bookFormDto , BindingResult bindingResult , 
			Model model , @RequestParam("bookImgFile") List<MultipartFile> bookImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "book/bookForm";
		}
		
		if(bookImgFileList.get(0).isEmpty()) {
			model.addAttribute("errorMessage", "책의 메인 이미지는 필수입니다.");
			return "book/bookForm";
		}
		
		try {
			bookService.saveBook(bookFormDto, bookImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage" , "상품 등록 중 에러가 발생했습니다");
			return "book/bookForm";
		}
		
		
		return "redirect:/";
	}
	
	
	
	
	
}
