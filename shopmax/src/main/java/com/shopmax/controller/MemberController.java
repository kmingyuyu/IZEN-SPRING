package com.shopmax.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopmax.dto.MemberFormDto;
import com.shopmax.entity.Member;
import com.shopmax.service.MemberService;
import com.shopmax.service.RamdomPassword;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	//문의하기
	@GetMapping(value = "/members/qa")
	public String qa() {
		return "member/qa";
	}
	
	//로그인 화면
	@GetMapping(value = "/members/login")
	public String loginMember() {
		
		return "member/memberLoginForm";
	}
	
	//회원가입 화면
	@GetMapping(value = "/members/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		
		return "member/memberForm";
	}
	
	//회원가입
	@PostMapping(value = "/members/new")
	public String memberForm(@Valid MemberFormDto memberFormDto, 
			BindingResult bindingResult, Model model) {
		//@Valid: 유효성을 검증하려는 객체 앞에 붙인다.
		//BindingResult: 유효성 검증 후의 결과가 들어있다.
		
		if(bindingResult.hasErrors()) {
			//에러가 있다면 회원가입 페이지로 이동
			return "member/memberForm";
		}
		
		
		try {
			//MemberFormDto -> Member Entity, 비밀번호 암호화
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);			
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}
		
		
		return "redirect:/";
	}

	//로그인 실패했을때
	@GetMapping(value="/members/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/memberLoginForm";
	}
	
	@GetMapping(value = "/findpw")
	public String search_ps(Model model) {
		model.addAttribute("loginFormDto", new MemberFormDto());
		return "login/findPwForm";
	}
	
	private final RamdomPassword randomPassword;
	
	
	// 비밀번호 찾고 난수생성기로 랜덤비밀번호 생성
	@PostMapping("/findpw")
	@ResponseBody
	public HashMap<String, String> memberps(@RequestBody Map<String, Object> psdata, Principal principal) {
		String email = (String) psdata.get("email");
		HashMap<String, String> msg = new HashMap<>();
		
		String pass = randomPassword.passwordFind(email);
		// pass 암호화된 비밀번호
		String ramdomps = randomPassword.getRamdomPassword(12);
		// ramdomps 를 view에 출력
		String password = randomPassword.updatePassword(ramdomps, email, passwordEncoder);
		
		randomPassword.sendEmail(email, "새로운 비밀번호", "새로운 비밀번호: " + ramdomps);
		
		String asd = "이메일로 임시 비밀번호가 발송되었습니다.";
		msg.put("message", asd);
		return msg;
	}
}
	

