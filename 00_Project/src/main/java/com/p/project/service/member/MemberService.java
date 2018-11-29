package com.p.project.service.member;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import com.p.project.model.member.MemberVO;

//비즈니스 로직. db연동 이외의 작업
public interface MemberService {
	//0회원가입
	//public void register_ok(MemberVO vo);
	public void register_ok(HashMap<String, Object> params);
	public boolean login_ok(String userId, String userPw);
	public void memberUpdate(HashMap<String, Object> params, String userId);
	//public int memberDelete(String UserId);
	public HashMap<String, Object> selectOne(String userId);
	public List<HashMap<String, Object>> selectAll();
	
	/*//0_00 idCheck id중복체크
	public void idCheck(String userId, int check);
	//01_01 회원 로그인 체크
	public boolean loginCheck(MemberVO vo, HttpSession session);
	//01_02 회원 로그인 정보
	public MemberVO viewMember(MemberVO vo);
	//02 회원 로그아웃
	public void logout(HttpSession session);*/
}
