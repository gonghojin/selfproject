package org.hojin.persistence;

import java.util.List;

import org.hojin.domain.BoardVO;
import org.hojin.domain.Criteria;
import org.hojin.domain.SearchCriteria;

public interface BoardDAO {
	public void insert(BoardVO vo);
	
	public BoardVO read(Integer bno);
	
	public List<BoardVO> readAll();
	
	public void update(BoardVO vo);
	
	public void delete(Integer bno);
	
	public List<BoardVO> listPage(int page) throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int countPaging() throws Exception;
	
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	public void updateReplyCnt(Integer bno, int amount) throws Exception;
	
	public void updateViewCnt(Integer bno) throws Exception;
	
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(Integer bno) throws Exception;
	
	public void deleteAttach(Integer bno) throws Exception;
	
	public void replaceAttach(String fullName, Integer bno) throws Exception;
}

