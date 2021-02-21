<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/top.jsp" />



<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<!---------------------------- -->
		<form method="POST" enctype="multipart/form-data">
			<table class="table table-condensed table-bordered">
				<thead>
					<tr></tr>
				</thead>
				<tbody>
					<tr>
						<td width="20%"><b>작성자</b></td>
						<td width="80%">${b.board_mid}</td>
					</tr>
					<tr>
						<td width="20%"><b>글제목</b></td>
						<td width="80%">${b.board_subject}</td>
					</tr>
					<tr>
						<td>이미지</td>
						<td><img width="150px" class="img img-responsive"
							src="../product_images/${b.board_file}"></td>
					</tr>
					<tr>
						<td width="20%"><b>글내용</b></td>
						<td width="80%">${b.board_content}</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<c:if test="${b.board_mid eq loginUser.userid}">
							<button class="btn btn-primary" type="button"
								onclick="del(${b.board_idx})">글삭제</button>
							<button class="btn btn-danger" type="button"
								onclick="location.href='boardChangeForm.do?idx=${b.board_idx}'">글수정</button>
								</c:if>
							<button class="btn btn-success">돌아가기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<form name="df">
			<input type="hidden" name="idx">
		</form>

		<form name="cf">
			<input type="hidden" name="idx">
		</form>

		<script type="text/javascript">

function del(idx){
	alert("글을 삭제합니다")
	df.idx.value=idx;
	df.action="boardDetailDelete.do";
	df.submit();
}




</script>



		<form action="reviewDelete.do" method="POST">
			<table class="table">
				<thead>
					<th width="10%">삭제</th>
					<th width="15%">댓글작성자</th>
					<th width="40%">내용</th>
					<th width="20%">작성일</th>
				</thead>
			</table>
			<div id="re_data"></div>
			<%--
			<table class="table" id="reTable">	
				<tbody>
				  <c:forEach var="r"  varStatus="st"  items="${re}">
					<tr>
						<c:if test="${r.review_mid} eq ${loginUser.userid}">
						<td width="10%"><button class="btn btn-primary">댓글삭제</button></td>
						</c:if>
						<td width="25%">${r.review_mid}</td>
						<td width="45%">${r.review_content}</td>
						<td width="20%">${r.review_date}</td>
					</tr>
				</c:forEach> 
				</tbody>
			</table>
			--%>
			<div id="rWrite">
		</form>
			<%-- <form method="post" action="reviewInsert.do?idx=${b.board_idx}&mid=${loginUser.userid}"> --%>
			<table class="table">
				<tr>
					<td><textarea name="review_content" id="review_content"
							rows="3" cols="20" class="form-control"></textarea></td>
					<td><button type="button" onclick="getReview()" class="btn btn-primary">댓글입력</button></td>
					
				</tr>
			</table>
			</div>
			<div id="rUpdate">
			</div>
		<!-- </form> -->
	
		

</div>
</div>

<script>
	

	function getReview(){
		$.ajax({
			type : 'GET',
			url : "reviewInsert.do?idx=${b.board_idx}&mid=${loginUser.userid}",
			data: $("#review_content").serialize(),    //seri
			dataType:'xml',
			success: function(data,status){
				//alert("ajax11"+data);
				var n=$(data).find('result').text();
				if(parseInt(n)>0){
					//alert('등록성공');
					//모든 데이터 가져오는 함수 호출
					getReviewList();
				}
				$("#review_content").val("");
			},
			error:function(e){
				alert('Error : '+e.status);
			}
		});
	}
	
	function getReviewList(){
		$.ajax({
			type : 'GET',
			url : "reviewList.do?idx=${b.board_idx}&mid=${loginUser.userid}",
			dataType : "html",
			cache : false,
			success: function(data){
				//alert(data);
				$('#re_data').html(data);
				
			},
			error:function(e){
				alert('Error : '+e.status);
			}
		});
	}//
	
	function getReviewList2(cp){
		//alert("getReviewList2");
		$.ajax({
			type : 'GET',
			url : "reviewList.do?idx=${b.board_idx}&mid=${loginUser.userid}&cpage="+cp,
			dataType : "html",
			cache : false,
			success: function(data){
				alert(data);
				$('#re_data').html(data);
				
			},
			error:function(e){
				alert('Error : '+e.status);
			}
		});
	}//
	
	function delReview(num, i){
		 var userid='${loginUser.userid}';
		 /* alert($('#mid'+i).text());
		 alert($('#mid'+i).text()==userid); */
		if($('#mid'+i).text()!= userid){
			alert('작성자만 삭제할수 있어요');
			return;
		} 
		//var board_num = num;
		 $.ajax({
			type : 'GET',
			url : "delReview.do?num="+num,
			dataType:'xml',		
			success: function(status){
				//alert(status)
				 alert("삭제완료");
				 getReviewList();
			},
			error:function(e){
				alert('Error : '+e.status);
			}
		});
	}//delReView
	
	
	
	function updateReviewForm(num,i){
		 var userid='${loginUser.userid}';
		if($('#mid'+i).text()!= userid){
			alert('작성자만 수정할수 있어요');
			return;
		} 
		$('#rnum').val(num);
		$.ajax({
			type : 'GET',
			url : "updateReview.do?num="+num,
			dataType:'html',
			success: function(data,status){
				//alert(data);
				$('#rWrite').hide();
				$('#rUpdate').html(data);
				$('#rUpdate').show();
			},
			error:function(e){
				alert('Error : '+e.status);
			}
		});
	}
	
	function updateReview(num){
		$.ajax({
			type : 'GET',
			url : "reviewUpdate.do?num="+num,
			data: $("#uf").serialize(),    //seri
			dataType:'xml',
			success: function(data,status){
				var n=$(data).find('result').text();
				if(parseInt(n)>0){
					alert("수정완료");
					$('#rUpdate').hide();
					$('#rWrite').show();
					getReviewList();
				}
				$("#review_content").val("");
			},
			error:function(e){
				alert('Error : '+e.status);
			}
		});
	}
	
	
	
	$(function(){
	    
	    getReviewList();
	    
	});
	
	
</script>
<jsp:include page="/foot.jsp" />