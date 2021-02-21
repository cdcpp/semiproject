<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/top.jsp"/>

<script type="text/javascript">

	$(function(){
		
		
	});//$(function) end
	
	function check(){
		if($('#upcode').val()!='1'){
			alert('카테고리를 지정해주세요');
			$('#upcode').focus();
			return false;
		}
		if($('#downcode').val()==''){
			alert('카테고리를 지정해주세요');
			$('#downcode').focus();
			return false;
		}
		if(!$('#pname').val()){
			alert('상품명을 입력하세요');
			$('#pname').focus();
			return false;
		}
		if(!$('#pimage1').val()){
			alert('이미지를 등록하세요');
			$('#pimage1').focus();
			return false;
		}
		if(!$('#price').val()){
			alert('가격을 입력하세요');
			$('#price').focus();
			return false;
		}
		if(!$('#pcont').val()){
			alert('상품설명을 입력하세요');
			$('#pcont').focus();
			return false;
		}
		alert('상품등록 성공');
		return true;
	}
</script>

<div class="row">
<div class="col-md-10 col-md-offset-1">
<h1>중고장터 상품등록</h1>

<!----------------------------  -->
<form  action="prodInsert.do" method="POST"
 enctype="multipart/form-data" onsubmit="return check()">
 <!-- 상품 이미지 파일을 업로드 하려면 enctype이 multipart/form-data여야 한다.  -->
<table class="table table-condensed table-bordered">
	<thead>
		<tr>
			<th colspan="2">
			<h3>상품등록</h3>
			</th>
		</tr>		
	</thead>
	<tbody>
		<tr>
			<td width="20%"><b>카테고리</b></td>
			<td width="80%">
			<div class="col-md-5" style="padding-left:0">
			<!-- 상위 카테고리----------------->
			<select name="upcode" id="upcode"
			 onchange="selectDCate(this.value)" class="form-control">
			 	<option value=''>::상위 카테고리::</option>
				<option value="1">의류</option>
			</select>
			
			
			</div>
			<div class="col-md-5" style="padding-left:5px">
			<span id="selectDcg"><!-- 하위 카테고리 --------------------->
				<select name="downcode" id="downcode"
			 onchange="" class="form-control">
			 	<option value=''>::하위 카테고리::</option>
				<option value="1">상의</option>
				<option value="2">하의</option>
				<option value="3">신발</option>
			</select></span>
			</div>
			</td>
		</tr>
		<tr>
			<td width="20%"><b>상품명</b></td>
			<td width="80%">
			<input type="text" name="pname" id="pname"  class="form-control">
			</td>
		</tr>
		<tr>
			<td>상품이미지</td>
			<td>
			<input type="file" name="pimage1" id="pimage1" class="form-control"><br>
			</td>
		</tr>
		<tr>
			<td width="20%"><b>상품가격</b></td>
			<td width="80%">
			<div class="col-md-5" style="padding-left:0">
			<input type="text" name="price" id="price" class="form-control"> 원
			</div>
			</td>
		</tr>
		<tr>
			<td width="20%"><b>상품설명</b></td>
			<td width="80%">
			<textarea name="pcont" id="pcont"
			 rows="5" cols="60"  class="form-control"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
			<button class="btn btn-success">상품등록</button>
			</td>
		</tr>
	</tbody>
</table> 					
</form>
</div>
</div>
<c:import url="/foot.jsp"/>


