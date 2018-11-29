<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<center>
		<h1>회원정보 수정</h1>
	<form method="post" action="memberUpdate.do">
	<div>
		아이디:
		<input type="text" name="userId" id="userId" value="${userId }">
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
		<input name="userName" id="userName" size="5" value="${userName }">
	</div>
	<div>
		이메일 입력:
		<input name="userEmail" id="userEmail" size="15" value="${userEmail }">
	</div>
	<div style="width:650px; text-align:center;">
		<input type="submit" value="수정완료">
		<input type="reset" value="다시작성">
		<input type="button" value="돌아가기" onClick="location='../board/list.do'">
	</div>
</form>
	</center>
</body>
</html>