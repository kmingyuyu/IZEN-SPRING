package com.shopmax.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopmax.dto.MemberFormDto;
import com.shopmax.entity.Member;
import com.shopmax.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
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
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto" , new MemberFormDto());
		return "members/memberForm";
	}
	
//	회원가입
	@PostMapping(value="/members/new")
	public String memberForm(MemberFormDto memberFormDto) {
//		MemberFormDto -> Member Entity , 비밀번호 암호화
		Member member = Member.createMember(memberFormDto, passwordEncoder);
		memberService.saveMember(member);
		return "redirect:/";
		
	}
	
//	로그인 실패했을떄
	@GetMapping(value="members/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg" ,"아이디 또는 비밀번호를 확인 해주세요");
		return "members/memberLoginForm";
	}
	
	
	
}
