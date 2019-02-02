<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
	#modDiv{
		width:300px;
		height:100px;
		background-color:gray;
		position:absolute;
		top:50%;
		left:50%;
		margin-top:-50px;
		margin-left:-150px;
		padding:10px;
		z-index:1000;
	}
</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Ajax Test Page</h2>

<!-- 댓글 등록하는 input Form -->
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

<!-- 댓글 페이징처리 위한 <ul>처리 -->
<ul class="pagination"></ul>

<!-- 수정, 삭제처리 위한 이벤트 작업-->
<div id='modDiv' style="display:none;">
	<div id="modDivEvent" ></div>
	<div>
		<input type='text' id='replytext'>
	</div>
	<div>
		<button type="button" id="replyModBtn">Modify</button>
		<button type="button" id="replyDelBtn">Delete</button>
		<button type="button" id="closeBtn">Close</button>
	</div>
</div>

<!-- jQuery 3.3.1 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<!-- getAllList() 함수 getJSON() 함수로 댓글 data 불러온다 댓글은 <li/> 태그 안에 위치함 -->
<script>
	var bno=200;
	
	getPageList(1); //JSP가 처음 동작하면 1페이지의 댓글을 가져오도록 코드 수정
	
	//댓글 리스트 목록 전체
	function getAllList(){
		//@RestController에서 객체를 JSON 방식으로 전달하기 때문에 jQuery로 호출할 때는 getJSON()이용
		$.getJSON("/replies/all/" + bno, function(data){
			var str="";
			console.log(data.length)//JSON으로 구성된 배열의 length가 출력됨
			
			//전체 댓글 목록 출력. 문자열을 구성해서 화면에 보이게 작성한다
			//Ajax로 호출된 목록에 대해서 루프를 돌면서 <li> 태그 생성
			$(data).each(
				function(){
					str += "<li data-rno='"+this.rno+"' class='replyLi'>" + this.rno + ":" + this.replytext + 
							"<button>Modify</button></li>";
			});
			
			$("#replies").html(str);
		});
	};
	
	//댓글 페이징 처리 위한 <ul>처리
	function getPageList(page){
		$.getJSON("/replies/" + bno + "/" + page , function(data){
			console.log(data.list.length);
			
			var str="";
			
			$(data.list).each(function(){
				str += "<li data-rno='"+this.rno+"' class='replyLi'>"+this.rno+":"+this.replytext+"<button>MOD</button></li>";
			});
			
			$("#replies").html(str);
			
			printPaging(data.pageMaker);
		});
	}
	
	//페이징 처리를 위해 만들어진 pageMaker 데이터를 이용한 printPaging()
	function printPaging(pageMaker){
		var str="";
		
		if(pageMaker.prev){
			str += "<li><a href='"+(pageMaker.startPage - 1)+"'> << </a></li>";
		}
		
		for(var i=pageMaker.startPage, len=pageMaker.endPage; i<=len; i++){
			var strClass=pageMaker.cri.page == i? 'class=active':'';
			str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
		}
		
		if(pageMaker.next){
			str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";	
		}
		
		$('.pagination').html(str);
	}
</script>

<!-- replyAddBtn click함수. ajax로 전달하면 controller에서 service-dao 작업을 거쳐 JSON객체 데이터 타입으로 넘겨가 등록된다 -->
<script>
	<!-- 댓글 데이터 조회,수정,삭체 처리. 버튼에 대한 이벤트 처리. -->
	$("#replies").on("click", ".replyLi button", function(){
		var reply=$(this).parent();

		var rno=reply.attr("data-rno");
		var replytext = reply.text();
		
		$("#modDivEvent").html(rno);
		$("#replytext").val(replytext);
		$("#modDiv").show("slow");
	});
	
	//사용자가 ADD REPLY 버튼을 클릭할 경우 이벤트 처리. 댓글 등록.
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
					getPageList(replyPage);
				}
			}
		});
	});

	//<div>에서 DELETE 버튼 클릭하면 삭제 처리 HTTP의 DELETE 방식으로
	$("#replyDelBtn").on("click", function(){
		var rno = $("#modDivEvent").html();
		var replytext = $("#replytext").val();
		
		$.ajax({
			type:'delete',
			url:'/replies/' + rno,
			headers:{"Content-Type" : "application/json", "X-HTTP-Method-Override" : "DELETE"},
			dataType:'text',
			success:function(result){
				console.log("result:"+result);
				if(result == 'SUCCESS'){
					alert("삭제 되었습니다");
					$("#modDiv").hide("slow");
					//getAllList();
					getPageList(replyPage);
				}
			}
		});
	});
	
	//PUT 수정처리. 데이터가 같이 전송된다
	$("#replyModBtn").on("click", function(){
		var rno=$("#modDivEvent").html();
		var replytext=$("#replytext").val();
		
		$.ajax({
			type:'PUT',
			url:'/replies/' + rno,
			headers:{"Content-Type":"application/json","X-HTTP-Method-Override":"PUT"},
			data:JSON.stringify({replytext:replytext}),
			dataType:'text',
			success:function(result){
				console.log("result:"+result);
				if(result=="SUCCESS"){
					$("#modDiv").hide("slow");
					//getAllList();
					getPageList(replyPage);
				}
			}
		});
	});
	
	//페이징 번호 클릭시 이벤트 처리
	var replyPage=1;
	
	$(".pagination").on("click", "li a", function(event){
		event.preventDefault();
		
		replyPage=$(this).attr("href");
		getPageList(replyPage);
	});
</script>
</body>
</html>