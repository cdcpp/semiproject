<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/top.jsp" />
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		
		<!---------------------------- -->
		<form name="cf" id="cf" action="boardChange.do?idx=${b.board_idx}" method="POST"
			enctype="multipart/form-data">
			<!-- 상품 이미지 파일을 업로드 하려면 enctype이 multipart/form-data여야 한다. -->
			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th colspan="2">
							<h3>글수정</h3>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="20%"><b>작성자</b></td>
						<td width="80%"><input type="text" name="board_mid"
							id="board_mid" class="form-control" value="${loginUser.userid}" readonly></td>
					</tr>
					<tr>
						<td width="20%"><b>글제목</b></td>
						<td width="80%"><input type="text" name="board_subject"
							id="board_subject" class="form-control"></td>
					</tr>
					<tr>
						<td>이미지</td>
						<td><input type="file" name="board_file" id="board_file"
							class="form-control"> <br></td>
					</tr>
					<tr>
						<td width="20%"><b>글내용</b></td>
						<td width="80%"><textarea name="board_content"
								id="board_content" rows="15" cols="60" class="form-control"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button class="btn btn-success">수정하기</button>
							<button class="btn btn-success" type="reset">다시작성</button>
							<button class="btn btn-success" onclick="back()">돌아가기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
<script>
	

	function back(){
		cf.action="../index.do";
		cf.submit();
	}
</script>
<jsp:include page="/foot.jsp" />