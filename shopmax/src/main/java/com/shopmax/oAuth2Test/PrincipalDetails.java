package com.shopmax.oAuth2Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.shopmax.entity.Member;

import lombok.Getter;
import lombok.ToString;

@Getter 
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User {

	 private Member member;
	 private Map<String, Object> attributes;
	
	 public PrincipalDetails(Member member) {
	        this.member = member;
	    }
	 
	 public PrincipalDetails (Member member, Oauth2UserInfo oAuth2UserInfo) {
	        //PrincipalOauth2UserService 참고
	        this.member = member;
	        this.attributes = oAuth2UserInfo.getAttributes();
	    }

	@Override
	public Map<String, Object> getAttributes() {
		 return attributes;
	}

	@Override
	public String getName() {
		String sub = attributes.get("sub").toString();
       return sub;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Collection<GrantedAuthority> collect = new ArrayList<>();
	        collect.add(new GrantedAuthority() {
	            @Override
	            public String getAuthority() {
	                return member.getRole().toString();
	            }
	        });
	        return collect;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		 return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		 return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		 return true;
	}

	@Override
	public boolean isEnabled() {
		 return true;
	}
	 
	
}
