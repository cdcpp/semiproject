<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/top.jsp"/>
<%
	String myctx = request.getContextPath();
%>

<body>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <img src="../product_images/${prod.pimage1}"
            class="img-responsive">
          </div>
          <div class="col-md-6">
            <h1>${prod.pname}</h1>
            <h3>${prod.price}원</h3>
            <p>${prod.pcont}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-3"></div>
          <div class="col-md-3">
          	<c:if test="${loginUser.userid ne null}">
            <a class="btn btn-warning" href="<%=myctx%>/shop/cartAdd.do?pnum=${prod.pnum}"/>장바구니</a>
            </c:if>
          </div>
          <div class="col-md-3">
            <a class="btn btn-danger" href="<%=myctx%>/index.do">돌아가기</a>
          </div>
          <div class="col-md-3"></div>
        </div>
      </div>
    </div>
 

<jsp:include page="/foot.jsp"/>


