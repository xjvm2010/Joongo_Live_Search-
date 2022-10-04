package com.jglive.www.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jglive.www.util.JonngoCrawling;
import com.jglive.www.util.DaangnCrawling;
import com.jglive.www.vo.SearchOption;

@Controller
@RequestMapping("/search")
public class SearchJoongo {
	
	@RequestMapping("/getContent")
	@ResponseBody
	public List<Map<String, Object>> getContent(SearchOption searchOption){
		DaangnCrawling dang = new DaangnCrawling();
		JonngoCrawling joongo = new JonngoCrawling();
		List<Map<String, Object>> result;
		result = joongo.getContent_jn(searchOption);
		result.addAll(dang.getContent_dg(searchOption));
		
		return result;
	}
	
	@RequestMapping("/getMoreContent")
	@ResponseBody
	public List<Map<String, Object>> getMoreContent(SearchOption searchOption){
		DaangnCrawling dang = new DaangnCrawling();
		JonngoCrawling joongo = new JonngoCrawling();
		
		searchOption.setQuantity(12);
		searchOption.setFirstQuantity(12);
		searchOption.setSearchQuantity(12);
		
		List<Map<String, Object>> result;
		result = joongo.getContent_jn(searchOption);
		result.addAll(dang.getContent_dg(searchOption));
		
		return result;
	}
}
