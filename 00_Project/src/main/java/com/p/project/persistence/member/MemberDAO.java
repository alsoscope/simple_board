package com.p.project.persistence.member;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import com.p.project.model.member.MemberVO;

public interface MemberDAO {
	//0회원가입
	//public void register_ok(MemberVO vo);
	public void register_ok(HashMap<String, Object> params);
	public int memberUpdate(HashMap<String, Object> params);
	//public int memberDelete(String userId);
	public HashMap<String, Object> selectOne(String userId);
	public List<HashMap<String, Object>> selectAll();

/*	//0_00 idCheck id중복체크
	public void idCheck(String userId, int check);
	//01_01 회원 로그인 체크
	public boolean loginCheck(MemberVO vo);
	//01_02 회원 로그인 정보
	public MemberVO viewMember(MemberVO vo);
	//02 회원 로그아웃
	public void logout(HttpSession session);*/
}
