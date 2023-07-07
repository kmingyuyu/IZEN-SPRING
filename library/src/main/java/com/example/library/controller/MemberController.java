package com.example.library.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.library.dto.MemberDto;
import com.example.library.entity.Member;
import com.example.library.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
//	로그인 화면
	@GetMapping(value="/member/login")
	public String login() {
		return "member/login";
	}
	
	
//	회원가입 화면
	@GetMapping(value="/member/new")
	public String member(Model model) {
		model.addAttribute("memberDto" , new MemberDto());
		return "member/memberNew";
	}
	
	@GetMapping(value="/member/new1")
	public String memberNew1(Model model) {
		return "member/new/memberNew1";
	}
	
	
	
	
	
	
//	회원가입 진행
	@PostMapping(value="/member/new")
	public String memberOk(@Valid MemberDto memberDto , BindingResult bindingResult , Model model) {
		
		if(bindingResult.hasErrors()) {
			return "member/memberNew";
		}
		
		try {
			Member member = Member.createMember(memberDto, passwordEncoder);
			memberService.saveMember(member);
		} catch (Exception e) {
			model.addAttribute("errorMessage" , e.getMessage());
			return "member/memberNew";
		}
		
		return "redirect:/";
	}
	
	
	
	
	
	
}
