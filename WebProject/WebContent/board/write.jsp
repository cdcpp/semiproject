<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/top.jsp" />

<script type="text/javascript">
	function check(){
		
		if($('#board_pk').val()=="1" && $('#board_mid').val()!="Admin"){
			alert('관리자만 작성할수 있습니다');
			$('#board_pk').focus();
			return false;
		}
		
		if($('#board_pk').val()=="0"){
			alert('게시판을 선택하세요');
			$('#board_pk').focus();
			return false;
		}
		
		
		if(!$('#board_subject').val()){
			alert('제목을 입력하세요');
			$('#board_subject').focus();
			return false;
		} 
		if(!$('#board_file').val()){
			alert('파일을 첨부해야 합니다.');
			$('#board_file').focus();
			return false;
		}
		if(!$('#board_content').val()){
			alert('글 내용을 입력하세요');
			$('#board_content').focus()
			return false;
		}
		
		alert("글 작성 완료");
		return true;
	}
</script>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		
	<form action="boardInsert.do" method="POST"
			enctype="multipart/form-data" onsubmit="return check()">

			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th colspan="2">
							<h3>글쓰기</h3>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="20%"><b>게시판 선택</b></td>
						<td width="80%">
						<select name="board_pk" id="board_pk">
						<option value="0">게시판을 선택하세요</option>
						<option value="1">공지사항</option>
						<option value="2">판매게시판</option>
						<option value="3">구매게시판</option>
						</select>
					</tr>
					<tr>
						<td width="20%"><b>작성자</b></td>
						<td width="80%"><input type="text" name="board_mid"
							id="board_mid" class="form-control" value="${loginUser.userid}" readonly></td>
					</tr>
					<tr>
						<td width="20%"><b>글제목</b></td>
						<td width="80%"><input type="text" name="board_subject"
							id="board_subject" class="form-control" value="${b.board_subject}"></td>
					</tr>
					<tr>
						<td>이미지</td>
						<td><input type="file" name="board_file" id="board_file"
							class="form-control"> <br></td>
					</tr>
					<tr>
						<td width="20%"><b>글내용</b></td>
						<td width="80%"><textarea name="board_content"
								id="board_content" rows="15" cols="60" class="form-control">${b.board_content}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button class="btn btn-success">저장하기</button>
							<button class="btn btn-success" type="reset">다시작성</button>
							<button class="btn btn-success" onclick="history.go(-1)">돌아가기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>

<jsp:include page="/foot.jsp" />