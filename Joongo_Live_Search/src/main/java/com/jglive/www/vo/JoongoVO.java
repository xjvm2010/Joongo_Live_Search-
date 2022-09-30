package com.jglive.www.vo;

public class JoongoVO {
	private int seq;
	private String title;
	private int price;
	private String mainLocationName;
	private String sortDate;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMainLocationName() {
		return mainLocationName;
	}
	public void setMainLocationName(String mainLocationName) {
		this.mainLocationName = mainLocationName;
	}
	public String getSortDate() {
		return sortDate;
	}
	public void setSortDate(String sortDate) {
		this.sortDate = sortDate;
	}
	
	@Override
	public String toString() {
		return "JoongoVO [seq=" + seq + ", title=" + title + ", price=" + price + ", mainLocationName="
				+ mainLocationName + ", sortDate=" + sortDate + "]";
	}
	
	
}
