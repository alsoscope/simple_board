package com.p.project.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.p.project.model.BoardVO;
import com.p.project.model.Criteria;
import com.p.project.model.SearchCriteria;
import com.p.project.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	private static final Logger logger=LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Inject
	private BoardDAO dao;
	
/*	@Test
	public void testListPage() throws Exception{
		int page=3;
		List<BoardVO> list=dao.listPage(page);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}*/
	
/*	@Test
	public void testListCriteria() throws Exception{
		Criteria cri=new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		
		List<BoardVO> list=dao.listCriteria(cri);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}*/
	
/*	@Test
	public void testURI()throws Exception{
		UriComponents uriComponents=UriComponentsBuilder.newInstance()
				.path("/board/view")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();
		
		logger.info("/board/view?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}*/
	
	/*@Test
	public void testsURI2()throws Exception{
		UriComponents uriComponents=UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("board","read")
				.encode();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}*/

	@Test
	public void testDynamic1() throws Exception{
		SearchCriteria cri=new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("k");
		cri.setSearchType("t");
		
		logger.info("=========================");
		
		List<BoardVO> list=dao.listSearch(cri);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno()+":"+boardVO.getTitle());
		}
		
		logger.info("=====================");
		
		logger.info("COUNT : "+dao.listSearchCount(cri));
	}
}
