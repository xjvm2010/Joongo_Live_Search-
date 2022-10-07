package com.jglive.www.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jglive.www.service.MemberService;
import com.jglive.www.service.SearchHistory;
import com.jglive.www.util.JonngoCrawling;
import com.jglive.www.vo.SearchOption;

@Controller
@RequestMapping("/search")
public class SearchJoongo {

	@Autowired
	JonngoCrawling joongo;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/getItem")
	@ResponseBody
	public List<Map<String, Object>> getItem(SearchOption searchOption, HttpSession session, Principal principa) {
		
		// 검색기록 저장
		searchOption.setUser_seq(0);
		
		memberService.SearchRecord(searchOption);
		
		System.out.print(searchOption.getSearchWord());

		List<Map<String, Object>> result;
		joongo.setSearchOption(searchOption);
		result = joongo.getItems();
		session.setAttribute("oldItemList", result);

		return result;
	}

	@RequestMapping("/getMoreItem")
	@ResponseBody
	public List<Map<String, Object>> getMoreItem(SearchOption searchOption) {

		searchOption.setQuantity(12);
		searchOption.setFirstQuantity(12);
		searchOption.setSearchQuantity(12);

		List<Map<String, Object>> result;
		joongo.setSearchOption(searchOption);

		result = joongo.getItems();

		return result;
	}

	@RequestMapping("/newItemSearch")
	@ResponseBody
	public List<Map<String, Object>> newItemSearch(SearchOption searchOption, HttpSession session) {

		List<Map<String, Object>> oldItemList = (List<Map<String, Object>>) session.getAttribute("oldItemList");
		searchOption.setQuantity(6);
		searchOption.setFirstQuantity(6);
		searchOption.setSearchQuantity(6);

		joongo.setSearchOption(searchOption);

		List<Map<String, Object>> newItemList = joongo.getNewItem(oldItemList);
		if (newItemList.size() > 0) {
			session.setAttribute("oldItemList", newItemList);
		}

		return newItemList;
	}
}
