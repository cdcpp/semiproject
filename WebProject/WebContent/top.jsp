<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String myctx = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css">
</head>

<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-ex-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=myctx%>/index.do"><span>중고장터</span></a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-ex-collapse">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${loginUser eq null}">
						<li class="active"><a href="<%=myctx%>/member/login.do">로그인</a>
						</li>
					</c:if>
					<c:if test="${loginUser ne null}">
						<li class="bg bg-info"><a href="#">${loginUser.userid}님
								로그인 중</a></li>
						<li><a href="<%=myctx%>/member/logout.do">로그아웃</a></li>
					</c:if>
					<c:if test="${loginUser eq null}">
					<li><a href="<%=myctx%>/member/join.do">회원가입</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="section section-success">
		<br> <br>
		<h1 class="text-center">중고장터</h1>
	</div>
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-ex-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-ex-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="<%=myctx%>/board/notice.do">공지사항</a></li>
					<li><a href="<%=myctx%>/search/search.do">네이버상품검색</a></li>
					<li><a href="<%=myctx%>/shop/list.do">상품목록</a></li>
					<c:if test="${loginUser eq null}">
					<li><a href="#" onclick="notUser()">상품등록</a></li>
					<li><a href="#" onclick="notUser()">장바구니</a></li>
					<li><a href="#" onclick="notUser()">판매게시판</a></li>
					<li><a href="#" onclick="notUser()">구매게시판</a></li>
					<li><a href="#" onclick="notUser()">회원정보</a></li>
					</c:if>
					<c:if test="${loginUser ne null}">
					<li><a href="<%=myctx%>/shop/input.do">상품등록</a></li>
					<li><a href="<%=myctx%>/shop/cart.do">장바구니</a></li>
					<li><a href="<%=myctx%>/board/sellBoard.do">판매게시판</a></li>
					<li><a href="<%=myctx%>/board/buyBoard.do">구매게시판</a></li>
					<li><a href="<%=myctx%>/member/memberinfo.do">회원정보</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<script>
	function notUser(){
	 alert("로그인해야 이용할수 있어요");
	 return;
 	}
 </script>