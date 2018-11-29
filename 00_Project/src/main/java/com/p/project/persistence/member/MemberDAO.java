package com.p.project.persistence.member;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import com.p.project.model.member.MemberVO;

public interface MemberDAO {
	//0ȸ������
	//public void register_ok(MemberVO vo);
	public void register_ok(HashMap<String, Object> params);
	public int memberUpdate(HashMap<String, Object> params);
	//public int memberDelete(String userId);
	public HashMap<String, Object> selectOne(String userId);
	public List<HashMap<String, Object>> selectAll();

/*	//0_00 idCheck id�ߺ�üũ
	public void idCheck(String userId, int check);
	//01_01 ȸ�� �α��� üũ
	public boolean loginCheck(MemberVO vo);
	//01_02 ȸ�� �α��� ����
	public MemberVO viewMember(MemberVO vo);
	//02 ȸ�� �α׾ƿ�
	public void logout(HttpSession session);*/
}
