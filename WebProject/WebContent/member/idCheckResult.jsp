<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
			function setUid(uid){
				//uid값을 팝업창을 띄워준 부모창의 userid 텍스트박스에 넣어주기
				
				// 펍업창 닫기
				
				//window=document=>form
				//부모창=>window객체=onpener
				opener.document.mf.userid.value=uid;
				//팝입청 닫기.
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
<body>
<c:if test="${result eq false}">
<div class="text-center">
	<h3>
		[<span class="text-danger">${msg}</span>]
	</h3>
	</div>
	<button onclick="setUid('${id}')" class="btn btn-primary">닫기</button>
	
</div>	
</c:if>
<c:if test="${result eq true}">
사용 불가

<form name="idf" action="idCheck.do" method="POST" class="form-inline">
		<label for="userid" class="col-md-2 text-right">아이디:</label>
		<div class="col-md-4">
			<input type="text" name="userid" placeholder="아이디를 입력하세요"
				class="form-control">
		</div>
		<div class="col-md-2">
			<button type="button" class="btn btn-info" onclick="send()">확인</button>
		</div>
	</form>
</c:if>
</body>
</html>

