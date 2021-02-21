<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%String myctx=request.getContextPath();%> 
  <jsp:include page="/top.jsp" />    
   <body>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h3>공지사항</h3>
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
            <li><a href="notice.do?cpage=${cpage-1}">prev</a></li>
           <c:forEach var="i" begin="1" end="${bPageCount}">
		   		<li <c:if test="${cpage eq i}">class='active'</c:if> >
		   			<a href="notice.do?cpage=${i}">${i}</a>
		   		</li>
		   		</c:forEach>
		   		<li><a href="notice.do?cpage=${cpage+1}">next</a></li>
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
   
    
  
  <jsp:include page="/foot.jsp" />
  