package com.jglive.www.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jglive.www.vo.CustomUserDetails;

@Component("authenticationProvider")
public class UserAuthProvider implements AuthenticationProvider {
	
	@Autowired
	private UserService userSerivce;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//사용자가 입력한 값
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
	
		//사용자가 입력한 아이디로 사용자의 정보를 가져옴
		CustomUserDetails userVo = (CustomUserDetails)userSerivce.loadUserByUsername(username);
		
		//사용자 입력한 비밀번호와 실제 DB에있는 값이 일치하는지 검사. 맞지않는경우 예외처리
	    if(!passwordEncoder.matches(password, userVo.getPassword())) {
	       throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
	    }
	    
		List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new UsernamePasswordAuthenticationToken(username, password, authorities); 
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
	     return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}


}
