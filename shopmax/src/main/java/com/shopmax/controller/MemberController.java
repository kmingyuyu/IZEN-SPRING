package com.shopmax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
//	문의하기
	@GetMapping(value="/members/qa")
	public String qa() {
		return "members/qa";
	}
	
//	로그인화면
	@GetMapping(value="/members/login")
	public String loginMember() {
		return "members/memberLoginForm";
	}
	
//	회원가입 화면
	@GetMapping(value="/members/new")
	public String memberForm() {
		return "members/memberForm";
	}
	
	
}
