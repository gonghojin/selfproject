package org.hojin.web;

import javax.inject.Inject;

import org.hojin.domain.Criteria;
import org.hojin.domain.ReplyVO;
import org.hojin.persistence.ReplyDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReplyTest {
	private static final Logger logger = LoggerFactory.getLogger(ReplyTest.class);
	
	
	@Inject
	private ReplyDAO dao;
	
	/*@Test
	public void testCreate() throws Exception{
		ReplyVO vo = new ReplyVO();
		vo.setRno(1);
		vo.setReplytext("수정확인");
		vo.setReplyer("공호진");
		
		dao.update(vo);
	}*/
	
	@Test
	public void testList() throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(10);
		dao.count(3);
	}
	
	
}
