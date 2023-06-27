package com.example.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {
	
	@GetMapping(value="/")
	public String main() {
		return "main";
	}
	
	
}
