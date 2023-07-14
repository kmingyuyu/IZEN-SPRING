package com.example.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {
	
	@GetMapping(value="/admin/book/new")
	public String bookForm(Model model) {
		
		
		
		return "book/bookForm";
	}
	
	
}
