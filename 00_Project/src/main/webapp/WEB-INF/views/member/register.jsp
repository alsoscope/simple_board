<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<%-- jQuery+Ajax로 id 중복체크 --%>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	function check(){//데이터 유효성 체크 하기 위한 함수
		if($('#userId').val()==''){
			alert("id를 입력 하세요.");
			$('#userId').val('').focus(); //지우고(val('')) 포커스 설정
			return false;
		}
	
		if($('#userPw').val()==''){
			alert("암호를 입력하세요.");
			$('#userPw').focus();
			return false;
		}
		
 		if($('#pwd_CHECK').val()==''){
			alert("암호확인을 입력하세요.");
			$('#pwd_CHECK').focus();
			return false;
		}
					//값을 얻어오는 val()
		if($('#userPw').val() != $('#pwd_CHECK').val()){
			alert("암호가 일치 하지 않습니다.");
			$('#pwd_CHECK').focus();
			return false;
		}
		
		if($('#userName').val()==''){
			alert("이름을 입력 하세요.");
			$('#userName').val.focus();
			return false;
		}
		
		if($('#userEmail').val()==''){
			alert("이메일 주소를 입력 하세요.");
			$('#userEmail').val.focus();
			return false;
		}
		return "register_ok.do";
	}//check()

	//-----------------------
	//ajax 이용하여 id사용 여부 체크
	//-----------------------
/* 	function confirmIDCheck(){
		if($('#userId').val()==''){
			alert("id를 입력하세요");
			$('#userId').focus();
		}else{
			//alert($('#id').val());
			//==jQuery.ajax({
			$.ajax({
				type : 'POST',
				url : 'idCheck.do',
				data : "userId="+$('#userId').val(),
				dataType : 'JSON', //서버에서 넘어오는 자료타입
				success : function(data){ //데이터를 받은 후
					//alert(data.check);
					if(data.check==-1){
						alert("사용중인 id입니다.");
						$('#userId').val("").focus();
					}else{
						alert("사용 가능한 id입니다.");
						$('#userPw').val("").focus();
					}//else
				}//success
			});
		}//else
	}//confirmIDCheck */
</script>
</head>
<body>
<form method="post" align="center;">
	<div>
		아이디 입력:
		<input name="userId" id="userId" size="10">
		<!-- <input type="button" id="btn" value="중복체크" onClick="confirmIDCheck()"> -->
	</div>
	<div>
		패스워드 입력:
		<input type="password" id="userPw" name="userPw" size="10">
	</div>
	<div>
		패스워드 입력 확인:
		<input type="password" id="pwd_CHECK" name="pwd_CHECK" size="10">
	</div>
	<div>
		이름 입력:
		<input name="userName" id="userName" size="5">
	</div>
	<div>
		이메일 입력:
		<input name="userEmail" id="userEmail" size="15">
	</div>
	<div style="width:650px; text-align:center;">
		<input type="button" value="회원가입" onClick="check()">
		<input type="reset" value="다시작성">
		<input type="button" value="가입안함" onClick="location='../board/list.do'">
	</div>
</form>
</body>
</html>