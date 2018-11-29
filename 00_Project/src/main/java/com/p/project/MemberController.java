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

//서비스 주입받아서 요청 받은 리퀘스트를 처리하기 위해 필요한 서비스의 메소드를 호출해서 결과 획득
//결과 데이터랑 적절한 페이지를 세팅하여 모델엔뷰를 리턴.
@Controller //현재 클래스를 스프링에서 관리하는 컨트롤러 bean으로 생성
@RequestMapping("/member/*") //모든 매핑은 /member/를 상속
public class MemberController {
	//로깅을 위한 변수
	private static final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	//0 회원가입 폼
	@RequestMapping(value="register.do", method=RequestMethod.GET)
	public String register() {
		return "member/register";
	}
	
	//0 회원가입 처리
	@RequestMapping(value="register_ok.do", method=RequestMethod.POST)
	public String register_ok(@RequestParam HashMap<String, Object> params) {		
		memberService.register_ok(params);
		return "redirect:../board/list.do";
	}
		
	/*@RequestMapping("login.do")
	 public String login(HttpSession session, String userId, String userPw) {
		 //성공 실패에 따라 리다이렉트 방향 결정
		 //session 사용은 매개변수에 session 참조변수를 두면 자동으로 들어옴
		 //login성공이면 main.do 리다이렉트, 실패면 form으로
		 if(service.login(userId,userPw)) {
			 session.setAtrribute("userId", id);
			 return "redirect:";
		 }
		 else {
			 return "redirect:";
		 }
	 }*/
	//01 로그인 화면
	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String login() {
		return "member/login"; //views/member/login.jsp로 포워드
	}
	//01 로그인 처리
	@RequestMapping(value="login_ok.do", method=RequestMethod.POST )
	public ModelAndView login_ok(HttpSession session, String userId, String userPw) {
		//성공 실패에 따라 리다이렉트 방향 결정
		//session 사용은 매개변수에 session 참조변수를 두면 자동으로 들어옴
		//login성공이면 main.do 리다이렉트, 실패면 form으로
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
			/*model.addAllAttributes(service.getMemberInfo(userId));//map을 통채로 싣기
			model.addAttribute(arg0); //모델 객체를 통채로 싣기
			model.addAttribute(arg0, arg1); //키 값 지정 하나의 데이터 싣기
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
	
	/*Spring MVC컨트롤러에서 사용할 수 있는 반환 유형 종류
	데이터와 패키지의 정보가 둘 다 있는 경우 ModelAndView()
	데이터에 대한 정보만 있는 경우 Model OR Map
	페이지에 대한 정보만 있는 경우 String
	둘 다 없는 경우 void
	
	응답할 페이지에 대한 정보가 없는 경우는 (void, Model, Map 경우)
	받은 url에서 . 이하를 제외한 문자열을 페이지 정보로 사용함 ex)joinForm.do -> joinForm
	
	join.do는 회원정보 데이터들을 파라미터로 받아서 db에 저장, loginForm.do 로 리다이렉트 해줘야함
	@RequestMapping("join.do")
	public String join(String userId, String userPw, String userName, String userEmail){
		System.out.println("userId");//파라미터 잘 들어오는지 확인
		//파라미터 이름이 똑같아야 함. 같지 않으면 null값 뜸
		@RequestParam("userId") String uid id라는 파라미터 값을 uid 매개변수에 넣음
		//required, value, defaultValue 라는 속성값이 있는데
		//requried값이 true일 경우 해당 파라미터가 없으면 요청처리 못함
		//defaultValue는 해당 파라미터 값이 들어오지 않았을 경우 지정한 디폴트 값으로 변수값 대체
		 
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

	/*//0_00 idCheck id중복체크
	@RequestMapping(value="idCheck.do",method=RequestMethod.POST)
	public String idCheck(String userId, int check) throws NamingException, IOException {
		memberService.idCheck(userId, check);
		return "member/idCheck";
	}
	
	//02 로그인 처리
	//로그인에 성공했으면서 사용자가 쿠키 사용 여부를 체크할 경우 -> 쿠키를 생성하고 세팅
	@RequestMapping(value="loginCheck.do", method=RequestMethod.POST)
	public String loginCheck(MemberVO vo, HttpSession session, HttpServletResponse response) {
		String returnURL="";
		if(session.getAttribute("userId")!=null) {
			//기존에 login이란 세션 값이 존재한다면
			session.removeAttribute("userId"); //기존값 제거
		}
		
		//로그인이 성공하면 MemberVo 객체 반환
		MemberVO vo2=service.login(vo);
		
		if(vo2 != null) { //로그인성공
			session.setAttribute("login", vo2);//세션에 login이란 이름으로 MemberVO 객체를 저장
			returnURL = "redirect:../board/list";//로그인 성공시 이동
			
			//-세션 추가되는 부분-
			//1. 로그인이 성공하면, 그 다음으로 로그인 폼에서 쿠키가 체크된 상태로 로그인 요청이 왔는지를 확인한다
			if(vo.isUseCookie()) {//vo클래스 안에 useCookie 항목에 폼에서 넘어온 쿠키사용 여부(true.false)가 들어있을 것임
				//쿠키 사용에 체크되어있으면 쿠키를 생성하고 현재 로그인이 되어 있을 때 생성되었던 세션의 id를 쿠키에 저장
				Cookie cookie=new Cookie("loginCookie", session.getUserId());
				//쿠키를 찾을 경로를 컨텍스트 경로로 변경
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);//단위는 초, 유효시간 7일
				//쿠키 적용
				response.addCookie(cookie);
			}
		}else { //로그인에 실패한 경우
			returnURL="redirect:../member/login"; //다시 로그인 폼으로
		}
		
		//로그인이 성공하면 
		return returnURL; //위에서 설정한 returnURL을 반환해서 이동시킴
	
				boolean result=memberService.loginCheck(vo, session);
		ModelAndView mav=new ModelAndView();
		if(result==true) { //로그인 성공
			//main.jsp 로 이동
			mav.setViewName("home");
			mav.addObject("msg", "success");
		}else { //로그인 실패
			//login.jsp 로 이동
			mav.setViewName("member/login");
			mav.addObject("msg", "failure");
		}
		return mav;
	}

	//03 로그아웃 처리
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
