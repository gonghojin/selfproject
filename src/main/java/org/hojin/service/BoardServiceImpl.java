package org.hojin.service;

import java.util.List;

import javax.inject.Inject;

import org.hojin.domain.BoardVO;
import org.hojin.domain.Criteria;
import org.hojin.domain.SearchCriteria;
import org.hojin.persistence.BoardDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void insert(BoardVO vo) throws Exception {
		boardDAO.insert(vo);
		
		String[] files = vo.getFiles();
		if(files == null){
			System.out.println("값이 없습니다~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			return;
		}
		
		for(String fileName: files){
			boardDAO.addAttach(fileName);
		}
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		boardDAO.updateViewCnt(bno);
		return boardDAO.read(bno);
	}

	@Override
	public List<BoardVO> readAll() throws Exception {
		return boardDAO.readAll();
	}
	
	@Transactional
	@Override
	public void update(BoardVO vo) throws Exception {
		boardDAO.update(vo);
		
		Integer bno = vo.getBno();
		
		boardDAO.deleteAttach(bno);
		
		String[] files = vo.getFiles();
		
		if(files == null){
			return;
		}
		
		for(String fileName: files){
			boardDAO.replaceAttach(fileName, bno);
		}
	}
	
	@Transactional
	@Override
	public void delete(Integer bno) throws Exception {
		boardDAO.deleteAttach(bno);
		boardDAO.delete(bno);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return boardDAO.listCriteria(cri);
	}

	@Override
	public int listCountCriterial() throws Exception {
		return boardDAO.countPaging();
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return boardDAO.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return boardDAO.listSearchCount(cri);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return boardDAO.getAttach(bno);
	}
		
}
