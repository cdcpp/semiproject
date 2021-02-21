<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.*"%>

<%String myctx=request.getContextPath();%>


<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=myctx%>/css/style.css" rel="stylesheet" type="text/css">


<form name="idf" action="idCheck.do" method="POST" class="form-inline">
	<label for="userid" class="col-md-2 text-right">아이디:</label>
	<div class="col-md-4">
		<input type="text" name="userid" placeholder="아이디를 입력하세요"
			class="form-control">
	</div>
	<div class="col-md-2">
		<button type="button" class="btn btn-success" onclick="send()">확인</button>
	</div>
</form>

<script type="text/javascript">
			function setUid(uid){
				//uid값을 팝업창을 띄워준 부모창의 userid 텍스트박스에 넣어주기
				
				// 펍업창 닫기
				
				//window=document=>form
				//부모창=>window객체=onpener
				opener.document.mf.userid.value=uid;
				//팝업창 닫기.
				self.close();
			}


			function send(){
				if(!idf.userid.value){
					alert('아이디를 입력하세요');
					idf.userid.focus();
					return;
				}
				idf.submit();
			}
		</script>