package com.p.project.service.member;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.p.project.model.member.MemberVO;
import com.p.project.persistence.member.MemberDAO;

@Service //���� Ŭ������ ���������� �����ϴ� service bean���� ���
public class MemberServiceImpl implements MemberService{
	@Inject
	MemberDAO memberDao;

	public String getData() {
		return "Before we turn to stone";
	}

	@Override
	public void register_ok(HashMap<String, Object> params) {
		/*		MemberVO vo2=viewMember(vo);
		//���� ���� ���
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

	//ȸ�� �� ���� ������ ��������
	public HashMap<String, Object> getMemberInfo(String userId){
		return memberDao.selectOne(userId);
	}
	
	public void memberUpdate(HashMap<String, Object> params, String userId) {
		if(params.get("userPw").equals(params.get("pwd_CHECK"))) {
			HashMap<String, Object> record=memberDao.selectOne((String)params.get(userId));
			record.putAll(params); //���� �ִ� �Ϳ��� ���� ������ ����
			memberDao.memberUpdate(record);
			//admin ���� ������Ʈ�� �� �ֵ��� record ���
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

	/*//01_01 ȸ�� �α��� üũ
	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) {
		boolean result=memberDao.loginCheck(vo);
		if(result) { //true�� ��� ���ǿ� ���
			MemberVO vo2=viewMember(vo);
			//���� ���� ���
			session.setAttribute("userid", vo2.getUserId());
			session.setAttribute("userName", vo2.getUserName());
		}
		return result;
	}

	//01_02 ȸ�� �α��� ����
	@Override
	public MemberVO viewMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDao.viewMember(vo);
	}

	//02 ȸ�� �α׾ƿ�
	@Override
	public void logout(HttpSession session) {
		//���� ���� ���� ����
		//session.removeAttribute("userId");
		//���� ������ �ʱ�ȭ ��Ŵ
		session.invalidate();
	}
	
	@Override
	public void idCheck(String userId, int check) {
		// TODO Auto-generated method stub
		check=-1;
		
		if(userId==null) {
			check=1; //��� ������ id
		}

		memberDao.idCheck(userId, check);
	}*/
}
