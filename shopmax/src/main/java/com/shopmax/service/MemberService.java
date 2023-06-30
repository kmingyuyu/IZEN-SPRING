package com.shopmax.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopmax.entity.Member;
import com.shopmax.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional //쿼리문 수행시 에러가 발생하면 변경된 데이터를 이전상태로 콜백시켜줌
@RequiredArgsConstructor //@Autowired를 사용하지 않고필드의 의존성을 주입시켜준다.
public class MemberService implements UserDetailsService {
	
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

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByEmail(email); // 사용자가 입력한 email이 db에 있는지 쿼리문을 사용한다.
		
		if(member == null) { // 사용자가 없다면
			throw new UsernameNotFoundException(email);
		}
		
//		사용자가 있다면 userDetails 객체를 만들어서 반환
		return User.builder()
			   .username(member.getEmail())
			   .password(member.getPassword())
			   .roles(member.getRole().toString())
			   .build();
	}

}