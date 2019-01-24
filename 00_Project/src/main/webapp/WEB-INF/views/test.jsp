<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Ajax Test Page</h2>

<!-- 전체 댓글 목록 출력 -->
<ul id="replies">
</ul>

<!-- jQuery 3.3.1 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	var bno=200;
	
	//@RestController에서 객체를 JSON 방식으로 전달하기 때문에 jQuery로 호출할 때는 getJSON()이용
	$.getJSON("/replies/all/" + bno, function(data){
		var str="";
		console.log(data.length)//JSON으로 구성된 배열의 length가 출력됨
		
		//전체 댓글 목록 출력. 문자열을 구성해서 화면에 보이게 작성한다
		//Ajax로 호출된 목록에 대해서 루프를 돌면서 <li> 태그 생성
		$(data).each(
			function(){
				str += "<li data-rno='"+this.rno+"' class='replyLi'>" + this.rno + ":" + this.replytext + "</li>";
		});
		$("#replies").html(str);
	});
</script>
</body>
</html>