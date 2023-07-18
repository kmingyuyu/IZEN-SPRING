package com.example.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final BookService bookService;
	
	@GetMapping(value="/")
	public String main() {
		
		
		
		
		
		
		
		
		
		return "main";
	}
	
	
	
	
	
	
	
}
