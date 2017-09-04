package org.hojin.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hojin.domain.BoardVO;
import org.hojin.domain.Criteria;
import org.hojin.domain.SearchCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{
	private final String namespace = "org.hojin.mappers.BoardMapper";
	
	@Inject
	private SqlSession session;
	
	
	@Override
	public void insert(BoardVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public BoardVO read(Integer bno) {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public List<BoardVO> readAll() {
		return session.selectList(namespace + ".readAll");
	}

	@Override
	public void update(BoardVO vo) {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer bno) {
		session.delete(namespace + ".delete", bno);
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if(page <= 0){
			page = 1;
		}
		
		page = (page -1) * 10;
		
		return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + ".listCriteria", cri);
	}

	@Override
	public int countPaging() throws Exception {
		return session.selectOne(namespace + ".countPaging");
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("amount", amount);
		
		session.update(namespace + ".updateReplyCnt", map);
		
		
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		session.update(namespace + ".updateViewCnt", bno);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		System.out.println(fullName + "..........................................");
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return session.selectList(namespace + ".getAttach", bno);
	}

	@Override
	public void deleteAttach(Integer bno) throws Exception {
		session.delete(namespace + ".deleteAttach", bno);
	}

	@Override
	public void replaceAttach(String fullName, Integer bno) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("fullName", fullName);
		map.put("bno", bno);
		
		session.insert(namespace + ".replaceAttach", map);
	}
	
	

}
