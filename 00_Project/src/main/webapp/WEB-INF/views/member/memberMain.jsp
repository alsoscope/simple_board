<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member Main</title>
</head>
<body>
	<h2>회원 메인</h2>
	${userId }님 로그인
	${userName }인 환영합니다
	이메일 : ${userEmail }
	<input type="button" value="로그아웃" onclick="location.href='logout.do'">
	<input type="button" value="회원보기" onclick="location.href='selectAll.do'">
	<input type="button" value="수정하기" onclick="location.href='memberUpdateForm.do'">
</body>
</html>