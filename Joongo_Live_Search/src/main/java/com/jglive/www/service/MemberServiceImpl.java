package com.jglive.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jglive.www.vo.MemberVO;
import com.jglive.www.vo.SearchOption;
import com.jglive.www.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memDAO;
	
	//회원가입 로직
    @Override
	public int register(MemberVO memVo) {
    	return memDAO.register(memVo);
	}

	//저장
    @Override
	public int SearchRecord(SearchOption searchOption) {
    	return memDAO.searchRecord(searchOption);
    };
	/*
	 * //검색
	 * 
	 * @Override public SearchOption getSearchHistory(int user_seq) { return }; //삭제
	 * 
	 * @Override public void deleteSearchHistory(int user_seq) {
	 * 
	 * };
	 */

    


}
