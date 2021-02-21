<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

			<table class="table" id="reTable">	
				<tbody>
				  <c:forEach var="r"  varStatus="st"  items="${re}">
					<tr>
						<td width="10%"><a href="#re_data" onclick="delReview(${r.review_num})">[삭제]</a>     <a href="#" onclick="updateForm(${r.review_num})">[수정]</a></td>
						<td width="15%">${r.review_mid}</td>
						<td width="40%">${r.review_content}</td>
						<td width="20%">${r.review_date}</td>
					</tr>
				</c:forEach> 
				</tbody>
			</table>
			
			
		
			<form name="uf" id="uf">
    		<input type="text" name="num" id="rnum" value="${r.review_num}">
    		<table class="table">
				<tr>
					<td><textarea name="review_content" id="review_content"
							rows="3" cols="20" class="form-control">${r.review_content}</textarea></td>
					<td><button type="button" onclick="updateReview()" class="btn btn-primary">댓글수정</button></td>
					
				</tr>
			</table>
			</form>
			
			