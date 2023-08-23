package com.shopmax.service;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.shopmax.entity.Member;
import com.shopmax.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class RamdomPassword {
	private final MemberRepository memberRepository;

	public String getRamdomPassword(int size) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&' };

		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());

		int idx = 0;
		int len = charSet.length;
		for (int i = 0; i < size; i++) {
			// idx = (int) (len * Math.random());
			idx = sr.nextInt(len); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
			sb.append(charSet[idx]);
		}

		return sb.toString();
	}

	public String updatePassword(String pass, String email, PasswordEncoder passwordEncoder) {
		Member member = memberRepository.findByEmail(email);

		String password = member.updatePassword(pass, passwordEncoder);

		if (member == null) {
			return "일치하는 사용자가 없습니다";
		}

		// Update the password for the member
		member.setPassword(passwordEncoder.encode(pass));

		// Encode the new password
		memberRepository.save(member); // Save the updated member to the database

		return "비밀번호가 업데이트되었습니다";
	}

	

	public String passwordFind(String email) {

		Member member = memberRepository.findByEmail(email);

		if (member == null) {
			return "일치하는 사용자가 없습니다";
		}

		return member.getPassword();
	}


	private final JavaMailSender javaMailSender;

	public void sendEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}

}