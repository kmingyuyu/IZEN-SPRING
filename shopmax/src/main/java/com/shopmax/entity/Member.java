package com.shopmax.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopmax.constant.Role;
import com.shopmax.dto.MemberFormDto;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(unique = true) // 중복된 값이 들어올 수 없다
	private String email;

	private String password;

	private String address;

	private String provider;

	private String providerId;

	@Enumerated(EnumType.STRING)
	private Role role;

	// MemberFormDto를 -> Member 엔티티 객체로 변환
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		// 패스워드 암호화
		String password = passwordEncoder.encode(memberFormDto.getPassword());

		Member member = new Member(memberFormDto.getName() , memberFormDto.getEmail() , memberFormDto.getAddress());
		member.setPassword(password);
		member.setRole(Role.ADMIN); //관리자로 가입할때
//		member.setRole(Role.USER); // 일반 사용자로 가입할때

		return member;
	}
	
	public Member (String name , String email , String address) {
		this.name = name;
		this.email = email;
		this.address = address;
	}
	
	
	
	@Builder(builderClassName = "MemberDetailRegister", builderMethodName = "MemberDetailRegister")
    public Member(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String name, String password, String email, Role role, String provider, String providerId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

	
	
	
}
