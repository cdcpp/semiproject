<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%String myctx=request.getContextPath();%>
<jsp:include page="/top.jsp" />    
 <div align="center" class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1 table-responsive">

				<!-- 주문 폼 시작--------- -->
				<form name="orderF" id="orderF">
				<table class="table table-bordered">
					<tr class="success">
						<th class="text-center">번호</th>
						<th class="text-center">상품명</th>
						<th class="text-center">가격</th>
						<th class="text-center">삭제</th>
					</tr>
					<!-- ---------------- -->
					<c:forEach var="p"  varStatus="st"  items="${cartList}">
					<tr>
						<td>
						<input type="checkbox" name="pnum">
						${p.pnum}
						</td>
						<td>
						<img width="160px"
						  class="img img-responsive"
						 src="../product_images/${p.pimage1}">
						<br>
						${p.pname}
						</td>
						
						
						
				<td class="text-right">
				<b>
				<fmt:formatNumber value="${p.price}"
				 pattern="#,###"/>원<br>
			
				</b>	
					
					</td>
					<td>
			<a href="#" onclick="del(${p.pnum})"
			class="btn btn-default" role="button">삭제</a>
					</td>
					</tr>
					</c:forEach>
					<!-- ---------------- -->
					<tr>
						<td colspan="3" class="text-right">
						<b>장바구니 총액:</b>
						<span style="color:blue">
					<fmt:formatNumber value="${cartMap.TotalPrice}"
					 pattern="#,###"/>
						원
						</span><br>

				
						</td>
						<td colspan="3">
						<button type="button"
						 class="btn btn-warning">주문하기</button>
						<button type="button"
							onclick="history.go(-2)"
						 class="btn btn-success">계속쇼핑</button> 
						
						</td>
					</tr>
					
				</table>
				
				
				
				</form>
				<!-- 주문 폼 끝------------ -->
				
			</div>
		
		</div>
	
	</div>
</div>

<!-- -------------------------- -->
<form name="df">
	<input type="hidden" name="pnum">
</form>

<script type="text/javascript">

function del(pnum){
	df.pnum.value=pnum;
	df.action="cartDel.do";
	df.submit();
}

	
</script>

<jsp:include page="/foot.jsp" />






