package org.hojin.service;

import java.util.List;

import javax.inject.Inject;

import org.hojin.domain.Criteria;
import org.hojin.domain.ReplyVO;
import org.hojin.persistence.BoardDAO;
import org.hojin.persistence.ReplyDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replydao;
	
	@Inject
	private BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void create(ReplyVO vo) throws Exception {
		replydao.create(vo);
		boardDAO.updateReplyCnt(vo.getBno(), 1);
	}
	
	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return replydao.list(bno);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		replydao.update(vo);
	}
	
	@Transactional
	@Override
	public void delete(Integer rno) throws Exception {
		int bno = replydao.getBno(rno);
		replydao.delete(rno);
		boardDAO.updateReplyCnt(bno, -1);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		return replydao.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return replydao.count(bno);
	}
	
}
