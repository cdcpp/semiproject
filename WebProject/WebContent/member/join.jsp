<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:import url="/top.jsp"/>

    <script type="text/javascript">
    var win=null;
	function idCheck(){
		var url='idCheck.do';
		if(win==null){
		win = window.open(url,'idCheck','width=400, height=400, left=100, top=100, scrollbars=yes');
		}
	}//
	
	var zipwin=null;
	function zipCheck(){
		var url='zipCheck.do';
		if(zipwin==null){
			zipwin = window.open(url,'zipCheck','width=500, height=500, left=100, top=100 ,scrollbars=yes');
		}
	}
	
	
	
	function check(){
		
		var empJ = /\s/g;
		//아이디 정규식
		var idJ = /^[a-z0-9]{4,12}$/;
		// 비밀번호 정규식
		var pwJ = /^[A-Za-z0-9]{4,8}$/; 
		// 이름 정규식
		var nameJ = /^[가-힣]{2,6}$/;
		// 이메일 검사 정규식
		var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 휴대폰 번호 정규식
		var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
		
		 $('#name').blur(function(){
			if(nameJ.test($('#name').val())){
				console.log('true');
				$('#name_check').text('');
			}else{
				console.log('false');
				$('#name_check').text('한글만 입력할수 있어요');
				$('#name_check').css('color','red');
			}
		 });
		 
		 $('#password').blur(function(){
			if(pwJ.test($('#password').val())){
				console.log('true');
				$('#password_check').text('');
			}else{
				console.log('false');
				$('#password_check').text('특수문자,9자이상 사용할수 없어요');
				$('#password_check').css('color','red');
			}
		 });
		 
		 $('#email').blur(function(){
				if(mailJ.test($('#email').val())){
					console.log('true');
					$('#email_check').text('');
				}else{
					console.log('false');
					$('#email_check').text('이메일을 정확히 입력하세요');
					$('#email_check').css('color','red');
				}
			 });
		 
		
		
		
		
		if(!mf.name.value){
			alert('이름을 입력하세요');
			mf.name.focus();
			return false;
		}
		if(!mf.userid.value){
			alert('아이디를 입력하세요');
			mf.userid.focus();
			return false;
		}
		if(!mf.password.value){
			alert('비밀번호를 입력하세요');
			mf.password.focus();
			return false;
		}
		if(mf.password.value != mf.password2.value){
			alert('비밀번호가 달라요');
			mf.password2.select();
			return false;
		}
		if(!mf.hp1.value||!mf.hp2.value||!mf.hp3.value){
			alert("연락처를 모두 입력하세요");
			mf.hp1.focus();
			return false;
		}
		if(!mf.addr1.value||!mf.addr2.value){
			alert("주소를 모두 입력하세요");
			mf.addr2.focus();
			return false;
		}
		
		alert("회원가입 성공");
		return true;
	}
	
	
    </script>
    
  
	
	
	
	 <div class="row">
	
   <div class="text-center col-md-10 col-md-offset-1">
      <h1 align="left">회원가입</h1>
      <form name="mf"  method="POST" action="memberJoin.do" onsubmit="return check()">
        <table class="table">
          <tbody>
            <tr>
              <th width="20%">이름</th>
              <td width="80%">
                <input type="text" name="name" id="name" placeholder="이름을 입력하세요" class="form-control">
                <div class="check_font" id="name_check"></div>                
              </td>
            </tr>
            <tr>
              <th width="20%">아이디</th>
              <td width="80%">
                <div class="col-md-6" style="padding-left: 0px">
                  <input type="text" name="userid" placeholder="아이디를 입력하세요" class="form-control" readonly>
                </div>
                <div class="col-md-2">
                  <button type="button" id="idcheck" onclick="idCheck()"  class="btn btn-success">아이디 중복체크</button>
                </div>
              </td>
            </tr>
            <tr>
              <th width="20%">비밀번호</th>
              <td width="80%">
                <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요" class="form-control">
                <span class="text-info">*비밀번호는 4자이상 8자 이내여야 해요*</span>
                <div class="check_font" id="password_check"></div>
              </td>
            </tr>
            <tr>
              <th width="20%">비밀번호 확인</th>
              <td width="80%">
                <input type="password" name="password2" placeholder="비밀번호를 다시 입력하세요" class="form-control">
              </td>
            </tr>
            <tr>
              <th width="20%">이메일</th>
              <td width="80%">
                <input type="email" name="email" id="email" placeholder="이메일을 입력하세요" class="form-control">
                <div class="check_font" id="email_check"></div>
              </td>
            </tr>
            <tr>
              <th width="20%">연락처</th>
              <td width="80%">
                <div class="col-md-2" style="padding-left: 0px">
                  <input type="text" name="hp1" maxlength="3" class="form-control" placeholder="HP1">
                </div>
                <div class="col-md-2" style="padding-left: 0px">
                  <input type="text" name="hp2" maxlength="4" class="form-control" placeholder="HP2">
                </div>
                <div class="col-md-2" style="padding-left: 0px">
                  <input type="text" name="hp3" maxlength="4" class="form-control" placeholder="HP3">
                </div>
              </td>
            </tr>
            <tr>
              <th width="20%">우편번호</th>
              <td width="80%">
                <div class="col-md-6" style="padding-left: 0px">
                  <input type="text" name="zipcode" maxlength="5" readonly="" placeholder="우편번호를 입력하세요"
                  class="form-control">
                </div>
                <div class="col-md-2">
                  <button type="button" onclick="zipCheck()" class="btn btn-success">우편번호 찾기</button>
                </div>
              </td>
            </tr>
            <tr>
              <th width="20%">주소</th>
              <td width="80%">
                <input type="text" name="addr1" readonly class="form-control">
                <p style="margin: 10px"></p>
                <input type="text" name="addr2" placeholder="주소2를 입력하세요" class="form-control">
              </td>
            </tr>
            <tr>
              <td colspan="2" class="text-center">
                <button type="submit" class="btn btn-info">회원가입</button>
                <button type="reset" class="btn btn-warning">다시쓰기</button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
    </div>
       <c:import url="/foot.jsp"/>
    
    </body>
    

 
    
    
    
    
    
    
