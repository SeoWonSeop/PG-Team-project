package com.site.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.mapper.QnaMapper;
import com.site.vo.QnaVo;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaMapper qnaMapper;

	@Override		// qna 전체리스트 호출
	public Map<String, Object> qnaList(int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int limit = 10;
		
		int startRow = (page - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		List<QnaVo> list = qnaMapper.selectQnaList(startRow, endRow);
		int listCount = 0;	// 총 게시글 수
		listCount = qnaMapper.selectQnaCount();
		
		//하단 최대 넘버링페이지 
		int maxPage = (int)((double)listCount / limit + 0.99); 
		//하단 시작 넘버링페이지
		int startPage = (((int) ((double)page / 10 + 0.99)) - 1) * 10 + 1;
		//하단 끝 넘버링페이지
		int endPage = maxPage;
		// 1,2,3,4,5,6,7,8,9,10 -> 10개가 모두 있을 경우는 10을 endPage에 넣어줌.
		if (endPage > startPage + 10 - 1) {
			endPage = startPage + 10 - 1;
		}
		for(QnaVo qvo:list) {
			System.out.println("================================"+qvo);
		}
		map.put("list", list);
		map.put("page", page);
		map.put("listCount", listCount);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("maxPage", maxPage);
		
		return map;
	}

	@Override		// qna 검색리스트 호출
	public Map<String, Object> qnaListSearch(int page, String category, String search) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<QnaVo> list = null;
		
		int limit = 10;
		
		int startRow = (page - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		int listCount = 0;
		
		if(category.equals("all")) {
			list = qnaMapper.selectQnaListSearchAll(startRow, endRow, search);
			listCount = qnaMapper.selectQnaSearchAllCount(search);
		} else {
			list = qnaMapper.selectQnaListSearch(startRow, endRow, category, search);
			listCount = qnaMapper.selectQnaSearchCount(category, search);
		}
		
		//하단 최대 넘버링페이지 
		int maxPage = (int)((double)listCount / limit + 0.99); 
		//하단 시작 넘버링페이지
		int startPage = (((int) ((double)page / 10 + 0.99)) - 1) * 10 + 1;
		//하단 끝 넘버링페이지
		int endPage = maxPage;
		// 1,2,3,4,5,6,7,8,9,10 -> 10개가 모두 있을 경우는 10을 endPage에 넣어줌.
		if (endPage > startPage + 10 - 1) {
			endPage = startPage + 10 - 1;
		}
		
		map.put("list", list);
		map.put("page", page);
		map.put("listCount", listCount);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("maxPage", maxPage);
		map.put("category", category);
		map.put("search", search);
		
		return map;
	}

}
