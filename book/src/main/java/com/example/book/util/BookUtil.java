package com.example.book.util;

import org.springframework.stereotype.Service;

@Service
public class BookUtil {
	
	public int getPageCount(int numPerPage , int dataCount) {
		int pageCount = 0;
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			pageCount++;
		}
		
		return pageCount;
	}
	
	public String pageIndexList(int currentPage , int totalPage , String listUrl) {
		StringBuilder sb = new StringBuilder();
		int numperBlock = 5;
		int currentPageSetup;
		int page;
		
		if(listUrl.indexOf("?") != -1) {
//			?(쿼리스트링) 가 들어 있다면
			listUrl += "&";
			
		}else {
//			?(쿼리스트링) 가 없으면
			listUrl += "?";
		}
		
		currentPageSetup = (currentPage / numperBlock) * numperBlock;
		
		if(currentPage % numperBlock == 0) {
			currentPageSetup = currentPageSetup - numperBlock;
		}
		

		if(totalPage > numperBlock && currentPageSetup > 0) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">◀이전</a>&nbsp;");
		}
		
		page = currentPageSetup + 1;
				
		while(page <= totalPage && page <= (currentPageSetup + numperBlock)) {
			if(page == currentPage) {
//				현재 내가 선택한 페이지 라면
				sb.append("<font color=\"#9ED6FF\">" + page + "</font>&nbsp");
			}
			else {
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp");
				
				}
			
			page++;
			}
			
		if(totalPage - currentPageSetup > numperBlock) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>&nbsp;");
		}
		
		System.out.println(sb.toString());
		return sb.toString();
		
	}
	
	
}
