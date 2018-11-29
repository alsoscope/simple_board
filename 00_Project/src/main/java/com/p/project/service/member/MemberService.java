package com.p.project.service.member;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import com.p.project.model.member.MemberVO;

//����Ͻ� ����. db���� �̿��� �۾�
public interface MemberService {
	//0ȸ������
	//public void register_ok(MemberVO vo);
	public void register_ok(HashMap<String, Object> params);
	public boolean login_ok(String userId, String userPw);
	public void memberUpdate(HashMap<String, Object> params, String userId);
	//public int memberDelete(String UserId);
	public HashMap<String, Object> selectOne(String userId);
	public List<HashMap<String, Object>> selectAll();
	
	/*//0_00 idCheck id�ߺ�üũ
	public void idCheck(String userId, int check);
	//01_01 ȸ�� �α��� üũ
	public boolean loginCheck(MemberVO vo, HttpSession session);
	//01_02 ȸ�� �α��� ����
	public MemberVO viewMember(MemberVO vo);
	//02 ȸ�� �α׾ƿ�
	public void logout(HttpSession session);*/
}
