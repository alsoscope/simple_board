package com.p.project.persistence;

import java.util.List;

import com.p.project.model.Criteria;
import com.p.project.model.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> list(int bno) throws Exception;
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(int rno) throws Exception;
	
	//페이징처리
	public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception;
	public int count(int bno) throws Exception;
}
