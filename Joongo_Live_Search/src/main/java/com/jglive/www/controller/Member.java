package com.jglive.www.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jglive.www.service.MemberService;
import com.jglive.www.vo.MemberVO;

@Controller
public class Member {
	
	@Autowired
	MemberService memberService;
	
	/**
	 * 회원 등록 폼
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/join")
	@ResponseBody
	public ModelAndView memberJoin(MemberVO memVo, HttpSession session, Authentication authentication){
		
		//패스워드 암호화 처리
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		//패스워드 암호화 처리(시큐리티에서 제공)
		String endcodedPassword = passwordEncoder.encode(memVo.getPw());
		
		memVo.setPw(endcodedPassword);	
		
		//회원 DB저장
		memberService.register(memVo);
		
		ModelAndView mv = new ModelAndView();
		  
		//회원 가입 완료 시 메인으로 
		mv.setViewName("/");
		
		return mv;
	}
	
	
}
