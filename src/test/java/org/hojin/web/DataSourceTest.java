package org.hojin.web;

import java.util.List;

import javax.inject.Inject;

import org.hojin.domain.BoardVO;
import org.hojin.domain.SearchCriteria;
import org.hojin.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DataSourceTest {
		private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);

		@Inject
		private BoardDAO dao;
		
		/*@Test
		public void testInsert(){
			BoardVO vo =new BoardVO();
			vo.setTitle("test");
			vo.setContent("test");
			vo.setWriter("testWriter");
			dao.insert(vo);
		}
		}*/
		
		
	/*	@Test
		public void testPage() throws Exception{
			List<BoardVO> list = dao.listPage(1);
			for(BoardVO test: list){
				System.out.println(test.getBno() + ":" + test.getTitle());
			}
		}*/
		
	/*@Test
	public void testURI() throws Exception{
		//UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/board/read").queryParam("bno", 12).queryParam("perPageNum", 20).build();
		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/{module}/{page}").queryParam("bno", 12).queryParam("perPageNum", 20)
										.build().expand("board","read").encode();
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}*/
	
		@Test
		public void testSearchList() throws Exception{
				dao.updateViewCnt(409);
		/*	
			List<BoardVO> list = dao.listSearch(cri);
			
			for(BoardVO test: list){
				logger.info(test.getBno() + " : " + test.getTitle());
			}*/
		}
}		


