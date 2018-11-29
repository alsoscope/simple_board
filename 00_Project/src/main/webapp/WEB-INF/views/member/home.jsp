<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title><!-- 메인페이지 -->
</head>
<body>
<c:if test="${msg=='success' }">
	<h2>${sessionScope.userName }(${sessionScope.userId })님 환영합니다</h2>
</c:if>
</body>
</html>