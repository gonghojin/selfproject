package org.hojin.service;

import java.util.List;

import org.hojin.domain.Criteria;
import org.hojin.domain.ReplyVO;

public interface ReplyService {
	
	public void create(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> list(Integer bno) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer rno) throws Exception;
	
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
	
}
