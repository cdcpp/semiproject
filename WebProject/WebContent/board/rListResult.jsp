<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table" id="reTable">
	<tbody>
		<c:forEach var="r" varStatus="st" items="${rList}">
			<tr>
				
				<td width="10%"><a href="#re_data"
					onclick="delReview(${r.review_num},${st.index})">[삭제]</a> <a href="#"
					onclick="updateReviewForm(${r.review_num},${st.index})">[수정]</a></td>
				<td width="15%" id="mid${st.index}" name="mid">${r.review_mid}</td>
				<td width="40%">${r.review_content}</td>
				<td width="20%">${r.review_date}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<ul class="pagination">
				<li><a <%-- href="sellBoard.do?cpage= --%>onclick="getReviewList2(${cpage-1})">prev</a></li>
					<c:forEach var="i" begin="1" end="${rPageCount}">
						<li <c:if test="${cpage eq i}">class='active'</c:if>><a
							href= "#"<%-- "reviewList.do?cpage=${i}&idx=${ridx}" --%>  onclick="getReviewList2(${i})">${i}</a></li>
					</c:forEach>
				<li><a <%-- href="sellBoard.do?cpage=${cpage+1}" --%>onclick="getReviewList2(${cpage+1})">next</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<%-- ${b.board_idx}&mid=${loginUser.userid} --%>
