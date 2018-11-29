package com.p.project.persistence.member;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.p.project.model.member.MemberVO;

@Repository //���� Ŭ������ ���������� �����ϴ� dao bean���� ���
public class MemberDAOImpl implements MemberDAO{
	//SqlSession ��ü�� ���������� �����Ͽ� ����
	//�������� ����(Dependency Injection)
	@Inject
	SqlSession sqlSession; //mybatis ���� ��ü

	@Override
	public void register_ok(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		sqlSession.insert("member.register_ok", params);
	}

	@Override
	public int memberUpdate(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlSession.update("member.updateMember", params);
	}

/*	@Override
	public int memberDelete(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.delete("member.memberDelete", userId);
	}*/

	@Override
	public HashMap<String, Object> selectOne(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.selectOne", userId);
	}

	@Override
	public List<HashMap<String, Object>> selectAll() {
		//HashMap<String, String> map=new HashMap<String, String>();
		return sqlSession.selectList("member.selectAll");
	}

/*	//01_01 ȸ�� �α��� üũ
	@Override
	public boolean loginCheck(MemberVO vo) {
		String name=sqlSession.selectOne("member.loginCheck", vo);
		return (name==null)?false:true;
	}

	//01_02 ȸ�� �α��� ����
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return sqlSession.selectOne("member.viewMember", vo);
	}

	//02 ȸ�� �α׾ƿ�
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@Override
	public void idCheck(String userId, int check) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("member.idCheck", userId);
	}*/
}
