package com.shopmax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration // bean객체를 싱글톤으로 객체를 관리해준다.
@EnableWebSecurity // spring security filterChain이 자동으로 포함되게 한다.
public class SecurityConfig  {
	
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		로그인에 대한 설정
		
		http.authorizeHttpRequests(authorize -> authorize
				//페이지 접근권한에 관한 설정  : requestMatchers에 설정된 경로의 접근권한 허가(모든 사용자 비회원포함)
		.requestMatchers("/css/**","/js/**","/img/**","/images/**","/fonts/**").permitAll() 
		.requestMatchers("/","/members/**","/item/**").permitAll()
		.requestMatchers("/favicon.ico","/error").permitAll()
		.requestMatchers("/admin/**").hasRole("ADMIN")
		
		
		//admin으로 시작하는 경로는 관리자만 접근가능하도록 설정
		.anyRequest().authenticated() // 그 외에 페이지는 모두 로그인(인증을 받아야 함)
		
		)
		.formLogin(formLogin -> formLogin //로그인 관련 설정
				.loginPage("/members/login") //로그인 페이지 경로 설정
				.defaultSuccessUrl("/") // 로그인 성공시 이동할 페이지
				.usernameParameter("email")
				
				//로그인시 아이디 로 사용할 파라메터 이름 -> form 의 name으로 매치하므로 같은 이름 이여야함
				.failureUrl("/members/login/error")
				//로그인 실패시 이동할 경로 설정
				) 
		
		.logout(logout -> logout
		.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃시 이동할 경로 설정		
		.logoutSuccessUrl("/") // 로그아웃 성공시 이동할 경로 설정
		
		) //로그아웃 관련 설정
		
		.exceptionHandling(handling -> handling //인증되지 않은 사용자가 리소스에 접근했을때 설정(ex> 비회원이 주문목록에 접근 등등)
				.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				)
		.rememberMe(Customizer.withDefaults());
		
		return http.build();
	}
		
		
	
		
		
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
}
