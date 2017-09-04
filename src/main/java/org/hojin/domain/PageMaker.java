package org.hojin.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private int totalPages;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Criteria cri;

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData(){
		if(totalCount == 0){
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		}else{
			totalPages = totalCount / cri.getPerPageNum();
			if(totalCount % cri.getPerPageNum() > 0){
				totalPages++;
			}
			
			int modVal = cri.getPage() % displayPageNum;
			startPage = cri.getPage() / displayPageNum * displayPageNum + 1;
			
			if(modVal == 0){
				startPage -= displayPageNum;
			}
			
			endPage = startPage + 9;
			if(endPage > totalPages){
				endPage = totalPages;
			}
			
			prev = startPage == 1? false: true;
			next = endPage * cri.getPerPageNum() >= totalCount? false: true;
		}
	}
	
	public String makeQuery(int page){
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
									.queryParam("page", page)
									.queryParam("perPageNum", cri.getPerPageNum())
									.build();
		return uriComponents.toString();
	}
	
	public String makeSearch(int page){
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
									.queryParam("page", page).queryParam("perPageNum", cri.getPerPageNum())
									.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
									.queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword())).build();
		
		return uriComponents.toString();
	}
private String encoding(String keyword) {
		if(keyword == null || keyword.trim().length() == 0){
			return "";
		}
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
				
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	
	
}
