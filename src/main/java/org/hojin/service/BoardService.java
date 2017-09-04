package org.hojin.service;

import java.util.List;

import org.hojin.domain.BoardVO;
import org.hojin.domain.Criteria;
import org.hojin.domain.SearchCriteria;

public interface BoardService {
	public void insert(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public List<BoardVO> readAll() throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int listCountCriterial() throws Exception;
	
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	
	public int  listSearchCount(SearchCriteria cri) throws Exception;
	
	public List<String> getAttach(Integer bno) throws Exception;
	
	
	
}
