package com.p.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.p.project.model.Criteria;
import com.p.project.model.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace ="reply";
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return session.selectList(namespace+".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert("reply.create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		session.delete(namespace + ".delete", rno);
	}

	//����¡ ó��
	@Override
	public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace + ".listPage", paramMap);
	}

	@Override
	public int count(int bno) throws Exception {
		return session.selectOne("reply.count", bno);
	}

}
