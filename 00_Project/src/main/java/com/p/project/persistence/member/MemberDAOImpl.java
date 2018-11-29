package com.p.project.persistence.member;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.p.project.model.member.MemberVO;

@Repository //현재 클래스를 스프링에서 관리하는 dao bean으로 등록
public class MemberDAOImpl implements MemberDAO{
	//SqlSession 객체를 스프링에서 생성하여 주입
	//의존관계 주입(Dependency Injection)
	@Inject
	SqlSession sqlSession; //mybatis 실행 객체

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

/*	//01_01 회원 로그인 체크
	@Override
	public boolean loginCheck(MemberVO vo) {
		String name=sqlSession.selectOne("member.loginCheck", vo);
		return (name==null)?false:true;
	}

	//01_02 회원 로그인 정보
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return sqlSession.selectOne("member.viewMember", vo);
	}

	//02 회원 로그아웃
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
