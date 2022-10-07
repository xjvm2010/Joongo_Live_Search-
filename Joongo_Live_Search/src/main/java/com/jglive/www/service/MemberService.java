package com.jglive.www.service;

import com.jglive.www.vo.MemberVO;
import com.jglive.www.vo.SearchOption;

public interface MemberService {
	
	//회원가입
	public int register(MemberVO memVO);
	//저장
	public int SearchRecord(SearchOption searchOption);
	//검색
	//public SearchOption getSearchHistory(int user_seq);
	//삭제
	//public void deleteSearchHistory(int user_seq);

}
