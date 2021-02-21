<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<script type="text/javascript">
	function zipCheckResult(){
		
	}
</script>
</head>
<body>
	<form name="zipCheck" method="POST" action="zipCheckResult.do">
		<center>
			동입력 :
			<!-- 동 입력 상자 -->
			<input type="text" style="ime-mode: active;" id="dong" name="dong"></input>
			<!-- 검색 버튼 -->
			<input type="button" name="search" value="검색"
				onClick="zipCheckResult()"></input><br></br>
			<!--출력테이블-->
			<table border="1" width="100%">
				<th width="15%">우편번호</th>
				<th>주소</th>
			
	</form>
</body>
</html>