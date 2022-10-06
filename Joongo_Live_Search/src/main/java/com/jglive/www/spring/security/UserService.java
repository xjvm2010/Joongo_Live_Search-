package com.jglive.www.spring.security;

import com.jglive.www.dao.MemberDAO;
import com.jglive.www.vo.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
    MemberDAO memberDAO;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
    	CustomUserDetails userVo = memberDAO.loadUserByUserId(username);
    	
        if (userVo == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        
        return userVo;
    }

}
