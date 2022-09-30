package com.jglive.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JoongoLive {

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("JoongoLive");
		return mv;
	}
	
	@RequestMapping("/userSign")
	public ModelAndView userSign() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("userSign");
		return mv;
	}
	@RequestMapping("/userInfo")
	public ModelAndView userInfo() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("userInfo");
		return mv;
	}
	
	
}
