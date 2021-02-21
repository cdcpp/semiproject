<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>





<%-- ${newList} --%>
<div class="section">
	<div class="container">
	
	
		<div class="row">
			<div class="col-md-12">
				<h2><span class="label label-danger">상의</span></h3>
			</div>
		</div>
	
	
		<div class="row">
		<c:if test="${topList==null || empty topList}">
			<div class="col-md-3">
				<h3>상품 준비 중</h3>
			</div>
		</c:if>
		<c:if test="${topList !=null && not empty topList }">
			<c:forEach var="pd" items="${topList}" varStatus="status">        
			<!-- varStatus속성을 이용하면 반복문의 상태정보를 얻어낼 수 있다. 반목문 횟수: ${status.count}
				 인덱스 번호: ${status.index}   ${status.count}/${status.index}   (0~3 일때 4개만)
			 -->
			<div class="col-md-3">
				<%-- ${status.count}/${status.index}  --%>
				<a href="prodDetail.do?pnum=${pd.pnum}">
				<img
					src="../product_images/${pd.pimage1}"
					class="img-responsive" alt="상품이미지">
				</a>	
				<h2>${pd.pname}</h2>
				<p>
				<fmt:formatNumber value="${pd.price}" pattern="###,###"/>원<br>
				</p>
				
			</div> <!-- col-md-3 end -->
			<c:if test="${status.count mod 4 ==0 }">
				</div><!-- row end --><div class="row"><!-- 줄바꿈. 기존행을 닫고 새로운 행을 시작하자  -->
			</c:if>
			
			
			
			</c:forEach>
			</c:if>
			</div> <!-- row end -->
			</div> <!-- container end -->
			</div> <!-- section end -->
			
