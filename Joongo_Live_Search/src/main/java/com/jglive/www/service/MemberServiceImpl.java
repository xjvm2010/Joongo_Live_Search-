package com.jglive.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jglive.www.vo.MemberVO;
import com.jglive.www.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memDAO;
	
	//회원가입 로직
    @Override
	public MemberVO register(MemberVO memVo) {
    	return memDAO.register(memVo);
    	
	}


}
