package com.jglive.www.vo;

import java.util.Map;

public class SearchOption {
	private int user_seq;
	private int startIndex = 1;
	private String searchStartTime;
	private Map<String, Object> filter;
	private int searchQuantity =6;
	private int quantity =6;
	private int firstQuantity =6;
	private String searchWord;
	private String categoryName1;
	private String categoryName2;
	private String categoryName3;
	private int maxPrice = 2000000000;
	private int minPrice = 0;
	private int categoryDepth = 0;
	private int categorySeq = 0;
	
	
	
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getCategoryDepth() {
		return categoryDepth;
	}
	public void setCategoryDepth(int categoryDepth) {
		this.categoryDepth = categoryDepth;
	}
	public int getCategorySeq() {
		return categorySeq;
	}
	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String getSearchStartTime() {
		return searchStartTime;
	}
	public void setSearchStartTime(String searchStartTime) {
		this.searchStartTime = searchStartTime;
	}
	public Map<String, Object> getFilter() {
		return filter;
	}
	public void setFilter(Map<String, Object> filter) {
		this.filter = filter;
	}
	public int getSearchQuantity() {
		return searchQuantity;
	}
	public void setSearchQuantity(int searchQuantity) {
		this.searchQuantity = searchQuantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getFirstQuantity() {
		return firstQuantity;
	}
	public void setFirstQuantity(int firstQuantity) {
		this.firstQuantity = firstQuantity;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getCategoryName1() {
		return categoryName1;
	}
	public void setCategoryName1(String categoryName1) {
		this.categoryName1 = categoryName1;
	}
	public String getCategoryName2() {
		return categoryName2;
	}
	public void setCategoryName2(String categoryName2) {
		this.categoryName2 = categoryName2;
	}
	public String getCategoryName3() {
		return categoryName3;
	}
	public void setCategoryName3(String categoryName3) {
		this.categoryName3 = categoryName3;
	}
		
}
