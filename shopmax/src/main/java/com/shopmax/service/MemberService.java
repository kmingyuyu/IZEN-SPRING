package com.shopmax.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopmax.entity.Member;
import com.shopmax.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional //쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor //@Autowired를 사용하지 않고필드의 의존성을 주입시켜준다.
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member); //이메일 중복 체크
		Member savedMember = memberRepository.save(member);
		
		return savedMember;
	}
	
	//이메일 중복 체크
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
		
	}

}