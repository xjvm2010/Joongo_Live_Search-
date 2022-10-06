package com.jglive.www.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.jglive.www.vo.CustomUserDetails;
import com.jglive.www.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSession SqlSession;
	
	PasswordEncoder bcryptPasswordEncoder;
	
	public MemberVO register(MemberVO memVO) {
		SqlSession.insert("memberMapper.register",memVO);
		return SqlSession.selectOne("user_Mapper.login", memVO);
	}
	
	public CustomUserDetails loadUserByUserId(String id) {
		return SqlSession.selectOne("memberMapper.loadUserByUserId", id);
	}

}
