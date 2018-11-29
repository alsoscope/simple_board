<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 목록</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<%-- <%@ include file="../include/header.jsp" %> --%>
<script>
	$(document).ready(function(){
		$("#btnWrite").click(function(){
			//페이지 주소 변경(이동)
			location.href="write.do";
		});
	});
</script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시글 목록</h2>
<%-- <form name="form1" method="post" action="${path }/board/list.do">
	<select name="searchOption">
		<!-- 검색조건을 검색처리 후 결과화면에 보여주기 위해 c:out 출력태그 사용, 삼항연산자 -->
		<!-- 삼항연산자 문법 첫번째 연산자 ? 두번째 피연산자 : 세번째 피연산자;
			첫번째 연산자는 boolean값으로 평가됨, 첫번째 피연산자 값이 true면 두번째 피연산자 값이 반환,
			첫번째 연산자 값이 false면 세번째 피연산자 값 반환. 뒤에 나올 if 조건문과 비슷한 결과를 얻을  수 있음 -->
		<option value="all" <c:out value="${map.searchOption== 'all' ? "selected":""}"/>>제목+이름+제목</option>
		
		<option value="writer" <c:out value="${map.searchOption=='writer'?'selected':"}"/>>이름</option>
		<option value="content" <c:out value="${map.searchOption=='content'?'selected':"}"/>>내용</option>
		<option value="title" <c:out value="${map.searchOption=='title'?'selected':"}"/>>제목</option>	
	</select>
</form> --%>
<!-- 레코드의 개수를 출력 -->
${map.count }개의 게시물이 있습니다
<button type="button" id="btnWrite">글쓰기</button>
<table border="1" width="600px">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="row" items="${list }">
	<tr>
		<td>${row.bno }</td>
		<td><a href="${path }/board/view.do?bno=${row.bno}">${row.title }</a></td>
		<td>${row.writer }</td>
		<td>
			<!-- 원하는 날짜형식으로 출력하기 위해 fmt 태그 사용 -->
			<%-- <fmt:formatDate value="${row.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
			<fmt:formatDate value="${row.regdate }" type="both" dateStyle="short" timeStyle="short"/>
		</td>
		<td>${row.viewcnt }</td>
	</tr>			
	</c:forEach>
	
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${pageMaker.prev }">
				<li><a href="listPage?page=${pageMaker.startPage-1 }">&laquo;</a></li>
			</c:if>
			
			<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
				<li <c:out value="${pageMaker.cri.page==idx?'class=active':'' }"/>>
					<a href="listPage?page=${idx }">${idx }</a>			
				</li>
			</c:forEach>
			
			<c:if test="${pageMaker.next&&pageMaker.endPage>0 }">
				<li><a href="listPage?page=${pageMaker.endPage+1 }">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
</table>
</body>
</html>