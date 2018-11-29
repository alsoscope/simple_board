package com.p.project.service.member;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.p.project.model.member.MemberVO;
import com.p.project.persistence.member.MemberDAO;

@Service //현재 클래스를 스프링에서 관리하는 service bean으로 등록
public class MemberServiceImpl implements MemberService{
	@Inject
	MemberDAO memberDao;

	public String getData() {
		return "Before we turn to stone";
	}

	@Override
	public void register_ok(HashMap<String, Object> params) {
		/*		MemberVO vo2=viewMember(vo);
		//세션 변수 등록
		session.setAttribute("userid", vo2.getUserId());
		session.setAttribute("userName", vo2.getUserName());*/
		if(params.get("userPw").equals(params.get("pwd_CHECK"))) {
			memberDao.register_ok(params);
		}
		//memberDao.register_ok(vo);
	}

	public boolean login_ok(String userId, String userPw) {
		HashMap<String, Object> result=memberDao.selectOne(userId);
		if(result==null)
			return false;
		else {
			String Pwd=(String)result.get(userPw);
			if(Pwd==null)
				return false;
			else {
				if(Pwd.equals(userPw))
					return true;
				else
					return false;
			}
		}
	}
	
	public List<HashMap<String, Object>> selectAll(){
		return memberDao.selectAll();
	}

	//회원 한 명의 정보를 가져다줌
	public HashMap<String, Object> getMemberInfo(String userId){
		return memberDao.selectOne(userId);
	}
	
	public void memberUpdate(HashMap<String, Object> params, String userId) {
		if(params.get("userPw").equals(params.get("pwd_CHECK"))) {
			HashMap<String, Object> record=memberDao.selectOne((String)params.get(userId));
			record.putAll(params); //원래 있던 것에서 받은 것으로 수정
			memberDao.memberUpdate(record);
			//admin 값도 업데이트할 수 있도록 record 사용
		}
	}

/*	@Override
	public int memberDelete(String UserId) {
		// TODO Auto-generated method stub
		return ;
	}*/

	@Override
	public HashMap<String, Object> selectOne(String userId) {
		return memberDao.selectOne(userId);
	}

	/*//01_01 회원 로그인 체크
	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) {
		boolean result=memberDao.loginCheck(vo);
		if(result) { //true일 경우 세션에 등록
			MemberVO vo2=viewMember(vo);
			//세션 변수 등록
			session.setAttribute("userid", vo2.getUserId());
			session.setAttribute("userName", vo2.getUserName());
		}
		return result;
	}

	//01_02 회원 로그인 정보
	@Override
	public MemberVO viewMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDao.viewMember(vo);
	}

	//02 회원 로그아웃
	@Override
	public void logout(HttpSession session) {
		//세션 변수 개별 삭제
		//session.removeAttribute("userId");
		//세션 정보를 초기화 시킴
		session.invalidate();
	}
	
	@Override
	public void idCheck(String userId, int check) {
		// TODO Auto-generated method stub
		check=-1;
		
		if(userId==null) {
			check=1; //사용 가능한 id
		}

		memberDao.idCheck(userId, check);
	}*/
}
