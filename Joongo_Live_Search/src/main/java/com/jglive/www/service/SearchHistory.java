package com.jglive.www.service;

import com.jglive.www.vo.SearchOption;

public interface SearchHistory {
	public void SearchRecord(SearchOption searchOption);
	public SearchOption getSearchHistory(int user_seq);
	public void deleteSearchHistory(int user_seq);
}
