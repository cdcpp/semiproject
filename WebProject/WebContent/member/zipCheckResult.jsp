<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	div{width:100%;overflow-x:auto;}
</style>
<meta charset="UTF-8">
<title>우편번호 검색</title>

<script type="text/javascript">
function setAddr(zip,addr){
	//alert(addr);
	opener.document.mf.zipcode.value=0+zip;
	opener.document.mf.addr1.value=addr;
	
	
	self.close();
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
			<div>
			<table border="1" width="100%">
				<th width="15%">우편번호</th>
				<th>주소</th>
				<c:forEach items="${postArr}" var="list" varStatus="li">
				<tr><td name="zipcode" onclick="setAddr('${list.zipcode}','${list.addrs}')">0${list.zipcode}</td><td name="addr">${list.addrs}</td></tr>		
				</c:forEach>
			</table>
			</div>
	</form>
</body>
</html>