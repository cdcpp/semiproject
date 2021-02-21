<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp" />
<%String myctx=request.getContextPath();%>



  <form name="lf" method="POST" action="<%=myctx%>/member/memberlogin.do">
      <div class="section">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="userid" class="control-label">아이디</label>
                </div>
                <div class="col-sm-4">
                  <input type="text" class="form-control" name="userid" placeholder="아이디를 입력하세요">
                </div>
              </div>
            </div>
            <div class="form-group"></div>
          </div>
        </div>
      </div>
      <div class="section">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">비밀번호</label>
                </div>
                <div class="col-sm-4">
                  <input type="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3"></div>
          </div>
        </div>
      </div>
    </form>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-3">
            <a class="btn btn-primary" href="javascript:lf.submit();">로그인</a>
          </div>
          <div class="col-md-3">
            <a class="btn btn-primary" href="<%=myctx%>/member/join.do">회원가입</a>
          </div>
          <div class="col-md-3"></div>
          <div class="col-md-3"></div>
        </div>
      </div>
    </div>
    </body>



















<jsp:include page="/foot.jsp" />