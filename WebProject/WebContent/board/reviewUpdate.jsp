<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
			<form name="uf" id="uf">
    		 <%-- <input type="text" name="num" id="rnum" value="${r.review_num}">  --%>
    		<table class="table">
				<tr>
					<td><textarea name="review_content" id="review_content"
							rows="3" cols="20" class="form-control">${r.review_content}</textarea></td>
					<td><button type="button" onclick="updateReview(${r.review_num})" class="btn btn-primary">댓글수정</button></td>
					
				</tr>
			</table>
			</form>