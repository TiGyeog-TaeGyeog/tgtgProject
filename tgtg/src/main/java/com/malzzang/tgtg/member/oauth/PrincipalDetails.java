package com.malzzang.tgtg.member.oauth;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.malzzang.tgtg.member.Member;

import lombok.Data;

// Authentication 객체에 저장할 수 있는 유일한 타입
@Data
public class PrincipalDetails implements OAuth2User{
	
	private Member member;
	private Map<String, Object> attributes;
	
	// OAuth2 로그인시 사용 
	public PrincipalDetails(Member member, Map<String, Object> attributes) {
		this.member = member;
		this.attributes = attributes;
		System.out.println("OAuth2User === "+this.attributes);
	}
	
	public Member getMember() {
		return member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	// 리소스 서버로부터 받은 회원정보 
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return member.getMemberId();
	}

}