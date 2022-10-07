package com.jglive.www.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.jglive.www.vo.CustomUserDetails;
import com.jglive.www.vo.MemberVO;
import com.jglive.www.vo.SearchOption;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSession SqlSession;
	
	PasswordEncoder bcryptPasswordEncoder;
	
	public int register(MemberVO memVO) {
		return SqlSession.insert("memberMapper.register",memVO);
	}
	
	public CustomUserDetails loadUserByUserId(String id) {
		return SqlSession.selectOne("memberMapper.loadUserByUserId", id);
	}
	
	public int searchRecord(SearchOption searVO) {
		return SqlSession.insert("memberMapper.searchRecord",searVO);
	}

}
