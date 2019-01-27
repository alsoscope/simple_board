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

<div>
	<div>
		REPLYER <input type="text" name="replyer" id="newReplyWriter">
	</div>
	<div>
		REPLY TEXT <input type="text" name="replytext" id="newReplyText">
	</div>
	<button id="replyAddBtn">ADD REPLY</button>
</div>


<!-- 전체 댓글 목록 출력 -->
<ul id="replies">
</ul>

<!-- jQuery 3.3.1 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	//사용자가 ADD REPLY 버튼을 클릭할 경우 이벤트 처리
	$("#replyAddBtn").on("click", function(){
		var replyer=$("#newReplyWriter").val();
		var replytext=$("#newReplyText").val();
		
		$.ajax({
			type:'post',
			url:'/replies',
			headers:{"Content-Type":"application/json", "X-HTTP-Method-Override":"POST"},
			dataType:'text',
			data:JSON.stringify({
				bno:bno, replyer:replyer, replytext:replytext
			}),
			success:function(result){
				if(result=='SUCCESS'){
					alert("등록 되었습니다");
					getAllList();
				}
			}
		});
	});
</script>
<script>
	var bno=200;
	
	function getAllList(){
		
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
	
	};
</script>
</body>
</html>