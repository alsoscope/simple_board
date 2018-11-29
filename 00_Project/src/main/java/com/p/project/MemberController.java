package com.p.project;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.p.project.model.member.MemberVO;
import com.p.project.service.member.MemberService;

//���� ���Թ޾Ƽ� ��û ���� ������Ʈ�� ó���ϱ� ���� �ʿ��� ������ �޼ҵ带 ȣ���ؼ� ��� ȹ��
//��� �����Ͷ� ������ �������� �����Ͽ� �𵨿��並 ����.
@Controller //���� Ŭ������ ���������� �����ϴ� ��Ʈ�ѷ� bean���� ����
@RequestMapping("/member/*") //��� ������ /member/�� ���
public class MemberController {
	//�α��� ���� ����
	private static final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	//0 ȸ������ ��
	@RequestMapping(value="register.do", method=RequestMethod.GET)
	public String register() {
		return "member/register";
	}
	
	//0 ȸ������ ó��
	@RequestMapping(value="register_ok.do", method=RequestMethod.POST)
	public String register_ok(@RequestParam HashMap<String, Object> params) {		
		memberService.register_ok(params);
		return "redirect:../board/list.do";
	}
		
	/*@RequestMapping("login.do")
	 public String login(HttpSession session, String userId, String userPw) {
		 //���� ���п� ���� �����̷�Ʈ ���� ����
		 //session ����� �Ű������� session ���������� �θ� �ڵ����� ����
		 //login�����̸� main.do �����̷�Ʈ, ���и� form����
		 if(service.login(userId,userPw)) {
			 session.setAtrribute("userId", id);
			 return "redirect:";
		 }
		 else {
			 return "redirect:";
		 }
	 }*/
	//01 �α��� ȭ��
	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String login() {
		return "member/login"; //views/member/login.jsp�� ������
	}
	//01 �α��� ó��
	@RequestMapping(value="login_ok.do", method=RequestMethod.POST )
	public ModelAndView login_ok(HttpSession session, String userId, String userPw) {
		//���� ���п� ���� �����̷�Ʈ ���� ����
		//session ����� �Ű������� session ���������� �θ� �ڵ����� ����
		//login�����̸� main.do �����̷�Ʈ, ���и� form����
		ModelAndView mav=new ModelAndView();
		if(memberService.login_ok(userId, userPw)) {
			session.setAttribute("userId", userId);
			mav.setViewName("redirect:memberMain.do");
		}else {
			mav.setViewName("redirect:login.do");
		}
		return mav;
	}

	@RequestMapping("memberMain.do")
	public String memberMain(Model model, HttpSession session) {
		String userId=(String)session.getAttribute("userId");
		if(userId==null)
			return "redirect:login.do";
		else {
			/*model.addAllAttributes(service.getMemberInfo(userId));//map�� ��ä�� �Ʊ�
			model.addAttribute(arg0); //�� ��ü�� ��ä�� �Ʊ�
			model.addAttribute(arg0, arg1); //Ű �� ���� �ϳ��� ������ �Ʊ�
			 */
			model.addAllAttributes(memberService.selectOne(userId));
		}
		return "member/memberMain";
	}

	@RequestMapping("memberList.do")
	public ModelAndView dummy2() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("memberList", memberService.selectAll());
		mav.setViewName("selectAll");
		return mav;
	}
	
	/*Spring MVC��Ʈ�ѷ����� ����� �� �ִ� ��ȯ ���� ����
	�����Ϳ� ��Ű���� ������ �� �� �ִ� ��� ModelAndView()
	�����Ϳ� ���� ������ �ִ� ��� Model OR Map
	�������� ���� ������ �ִ� ��� String
	�� �� ���� ��� void
	
	������ �������� ���� ������ ���� ���� (void, Model, Map ���)
	���� url���� . ���ϸ� ������ ���ڿ��� ������ ������ ����� ex)joinForm.do -> joinForm
	
	join.do�� ȸ������ �����͵��� �Ķ���ͷ� �޾Ƽ� db�� ����, loginForm.do �� �����̷�Ʈ �������
	@RequestMapping("join.do")
	public String join(String userId, String userPw, String userName, String userEmail){
		System.out.println("userId");//�Ķ���� �� �������� Ȯ��
		//�Ķ���� �̸��� �Ȱ��ƾ� ��. ���� ������ null�� ��
		@RequestParam("userId") String uid id��� �Ķ���� ���� uid �Ű������� ����
		//required, value, defaultValue ��� �Ӽ����� �ִµ�
		//requried���� true�� ��� �ش� �Ķ���Ͱ� ������ ��ûó�� ����
		//defaultValue�� �ش� �Ķ���� ���� ������ �ʾ��� ��� ������ ����Ʈ ������ ������ ��ü
		 
		 service.joinMember(userId,userPw,userEmail,userName);
		 return "redirect:../member/";
	}
	*/
	
	
	@RequestMapping("memberUpdateForm.do")
	public String memberUpdateForm(Model model, HttpSession session) {
		String userId=(String)session.getAttribute("userId");
		if(userId==null)
			return "redirect:loginForm.do";
		model.addAllAttributes(memberService.selectOne(userId));//getMemberInfo
		return "memberUpdateForm";
	}
	
	@RequestMapping("memberUpdate.do")
	public String memberUpdate(@RequestParam HashMap<String, Object> params, String userId) {
		memberService.memberUpdate(params, userId);
		return "redirect:main.do";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		//session.invalidate();
		session.removeAttribute("userId");
		return "redirect:memberMain.do";
	}

	/*//0_00 idCheck id�ߺ�üũ
	@RequestMapping(value="idCheck.do",method=RequestMethod.POST)
	public String idCheck(String userId, int check) throws NamingException, IOException {
		memberService.idCheck(userId, check);
		return "member/idCheck";
	}
	
	//02 �α��� ó��
	//�α��ο� ���������鼭 ����ڰ� ��Ű ��� ���θ� üũ�� ��� -> ��Ű�� �����ϰ� ����
	@RequestMapping(value="loginCheck.do", method=RequestMethod.POST)
	public String loginCheck(MemberVO vo, HttpSession session, HttpServletResponse response) {
		String returnURL="";
		if(session.getAttribute("userId")!=null) {
			//������ login�̶� ���� ���� �����Ѵٸ�
			session.removeAttribute("userId"); //������ ����
		}
		
		//�α����� �����ϸ� MemberVo ��ü ��ȯ
		MemberVO vo2=service.login(vo);
		
		if(vo2 != null) { //�α��μ���
			session.setAttribute("login", vo2);//���ǿ� login�̶� �̸����� MemberVO ��ü�� ����
			returnURL = "redirect:../board/list";//�α��� ������ �̵�
			
			//-���� �߰��Ǵ� �κ�-
			//1. �α����� �����ϸ�, �� �������� �α��� ������ ��Ű�� üũ�� ���·� �α��� ��û�� �Դ����� Ȯ���Ѵ�
			if(vo.isUseCookie()) {//voŬ���� �ȿ� useCookie �׸� ������ �Ѿ�� ��Ű��� ����(true.false)�� ������� ����
				//��Ű ��뿡 üũ�Ǿ������� ��Ű�� �����ϰ� ���� �α����� �Ǿ� ���� �� �����Ǿ��� ������ id�� ��Ű�� ����
				Cookie cookie=new Cookie("loginCookie", session.getUserId());
				//��Ű�� ã�� ��θ� ���ؽ�Ʈ ��η� ����
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);//������ ��, ��ȿ�ð� 7��
				//��Ű ����
				response.addCookie(cookie);
			}
		}else { //�α��ο� ������ ���
			returnURL="redirect:../member/login"; //�ٽ� �α��� ������
		}
		
		//�α����� �����ϸ� 
		return returnURL; //������ ������ returnURL�� ��ȯ�ؼ� �̵���Ŵ
	
				boolean result=memberService.loginCheck(vo, session);
		ModelAndView mav=new ModelAndView();
		if(result==true) { //�α��� ����
			//main.jsp �� �̵�
			mav.setViewName("home");
			mav.addObject("msg", "success");
		}else { //�α��� ����
			//login.jsp �� �̵�
			mav.setViewName("member/login");
			mav.addObject("msg", "failure");
		}
		return mav;
	}

	//03 �α׾ƿ� ó��
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session) {
		memberService.logout(session);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("member.login");
		mav.addObject("msg", "logout");
		return mav;
	}
*/
}//MemberController
