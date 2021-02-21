<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%String myctx=request.getContextPath();%>
  <jsp:include page="/top.jsp" />    
   <body>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h3>구매게시판</h3>
            <table class="table">
              <thead>
                <tr>
                  <th>글번호</th>
                  <th>글제목</th>
                  <th>작성자</th>
                  <th>작성일</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="b"  varStatus="st"  items="${bList}">
                <tr>
                  <td>${b.board_idx}</td>
                  <td><a href="boardDetail.do?idx=${b.board_idx}">${b.board_subject}</a></td>
                  <td>${b.board_mid}</td>
                  <td>${b.board_date}</td>
                </tr>
               </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
       <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <ul class="pagination">
            <li><a href="buyBoard.do?cpage=${cpage-1}">prev</a></li>
           <c:forEach var="i" begin="1" end="${bPageCount}">
		   		<li <c:if test="${cpage eq i}">class='active'</c:if> >
		   			<a href="buyBoard.do?cpage=${i}">${i}</a>
		   		</li>
		   		</c:forEach>
		   	<li><a href="buyBoard.do?cpage=${cpage+1}">next</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-3"></div>
          <div class="col-md-3"></div>
          <div class="col-md-3"></div>
          <div class="col-md-3">
            <a class="btn btn-warning" href="<%=myctx%>/board/write.do">글쓰기</a>
          </div>
        </div>
      </div>
    </div>
    <div id="searchForm">
      <form class="text-center"  action="searchBoard.do" method="POST">
        <select name="opt">
          <option value="0">제목</option>
          <option value="1">내용</option>
          <option value="2">작성자</option>
        </select>
        <input type="text" size="20" name="condition">&nbsp;
        <input type="submit" value="검색">
        <input type="hidden" name="pk" value=3>
      </form>
    </div>
  
  <jsp:include page="/foot.jsp" />
  