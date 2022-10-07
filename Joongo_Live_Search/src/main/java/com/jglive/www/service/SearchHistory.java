package com.jglive.www.service;

import org.springframework.stereotype.Service;

import com.jglive.www.vo.SearchOption;

public interface SearchHistory {
	//저장
	public void SearchRecord(SearchOption searchOption);
	//검색
	public SearchOption getSearchHistory(int user_seq);
	//삭제
	public void deleteSearchHistory(int user_seq);
}
