<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.*"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="MemberDao" class="member.model.MemberDAO" scope="session"/>
<jsp:useBean id="MemberVO" class="member.model.MemberDAO" scope="session"/>
<%
	String myctx = request.getContextPath();
	
%>
<c:import url="/top.jsp" />
<div class="row">
<div class="text-center col-md-10 col-md-offset-1">
	<h1 align="left">회원정보</h1>
	<form name="mf" action="<%=myctx%>/member/MemberinfoEdit.do" method="POST"
		onsubmit="return check()">
		<table class="table">
			
			<tr>
				<th width="20%">이름</th>
				<td width="80%"><input type="text" name="name"
					value="${loginUser.name}" placeholder="이름을 입력하세요"
					class="form-control"></td>
			</tr>
			<tr>
				<th width="20%">아이디</th>
				<td width="80%"><div class="col-md-6" style="padding-left: 0px">
						<input type="text" <%-- readonly --%> name="userid"
							value="${loginUser.userid}" 
							class="form-control">
					</div>
					</td>
			</tr>
			<tr>
				<th width="20%">비밀번호</th>
				<td width="80%"><input type="password" name="pwd"
					placeholder="비밀번호를 입력하세요" class="form-control"> <span
					class="text-info">*비밀번호는 4자이상 8자 이내여야 해요*</span></td>
			</tr>
			<tr>
				<th width="20%">비밀번호 확인</th>
				<td width="80%"><input type="password" name="pwd2"
					placeholder="비밀번호를 다시 입력하세요" class="form-control"></td>
			</tr>
			<tr>
				<th width="20%">이메일</th>
				<td width="80%"><input type="email" name="email"
					value="${loginUser.email}" 
					class="form-control"></td>
			</tr>
			<tr>
				<th width="20%">연락처</th>
				<td width="80%">
					<div class="col-md-2" style="padding-left: 0px">
						<input type="text" name="hp1" maxlength="3"
							value="${loginUser.hp1}" class="form-control"
							>
					</div>
					<div class="col-md-2" style="padding-left: 0px">
						<input type="text" name="hp2" maxlength="4"
							value="${loginUser.hp2}" class="form-control"
							>
					</div>
					<div class="col-md-2" style="padding-left: 0px">
						<input type="text" name="hp3" maxlength="4"
							value="${loginUser.hp3}" class="form-control"
							>
					</div>
				</td>
			</tr>
			<tr>
				<th width="20%">우편번호</th>
				<td width="80%"><div class="col-md-6" style="padding-left: 0px">
						<input type="text" name="zipcode" value=""
							maxlength="5" readonly placeholder="우편번호를 입력하세요"
							class="form-control">
					</div>
					<div class="col-md-2">
						<button type="button" onclick="" class="btn btn-success">
							우편번호 찾기</button>
					</div></td>
			</tr>
			<tr>
				<th width="20%">주소</th>
				<td width="80%"><input type="text" name="addr1"
					value="${loginUser.addr1}" placeholder="주소1을 입력하세요"
					class="form-control">
					<p style="margin: 10px"></p> <input type="text" name="addr2"
					value="${loginUser.addr2}" placeholder="주소2를 입력하세요"
					class="form-control"></td>
			</tr>
			

			</tr>
			<tr>

				<td colspan="2" class="text-center"><button type="submit"
						class="btn btn-info">회원정보수정</button>
					<button type="reset" class="btn btn-warning">다시쓰기</button></td>
			</tr>
		</table>
	</form>
</div>
</div>
<c:import url="/foot.jsp" />

