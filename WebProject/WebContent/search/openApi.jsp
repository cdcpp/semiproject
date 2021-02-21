<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	 <%String myctx=request.getContextPath();%>

<c:import url="/top.jsp"/>

<script type="text/javascript">
	//3rfay8xcs0RC2qwWW5SJ
	//L30wMHd7eF
	$(function() {
		$('#openBtn').click(function() {
			//사용자가 입력한 검색어 받기
			var keyword = $('#search').val();
			if (!keyword) {
				alert('검색어를 입력하세요');
				$('#search').focus();
				return;
			}//if--

			send(keyword, 1);
		});
	});
	function send(keyword, start) {
		$.ajax({
			type : 'GET',
			url : 'openApiResult.jsp?keyword=' + encodeURIComponent(keyword)
					+ "&start=" + start,
			dataType : 'json',
			cache : false,
			success : function(res) {
				//alert(JSON.stringify(res.items));
				var cnt = res.total;//검색결과 갯수
				var pageSize = 10;//한 페이지당 보여줄 상품갯수
				showData(res.items);
				showPage(cnt, pageSize, keyword);
				$('#a' + start).addClass('active');
			},
			error : function(e) {
				alert('error: ' + e.status);
			}
		});//ajax end
	}

	function showData(items) {
		//items는 배열
		var str = "<table class='title' border='1px solid black'>"
		str += "<tr>"
		//$.each(배열,함수(index,배열요소){})
		$.each(items, function(i, shop) {
			//console.log(book.title);
			str += "<td width='20%' >";
			str += "<a href='"+shop.link+"' target='_blank'>";
			str += "<img src='"+shop.image+"' class='img img-thumbnail'>"
			str += "</a><br>"
			str += shop.title + "<br>";

			str += "<p>최고가: " + shop.hprice + "원" + "</p>";

			str += "<p>최저가: " + shop.lprice + "원" + "</p>";
			str += "<p>쇼핑몰: " + shop.mallName + "</p>"

			str += "</td>"
			if (i % 5 == 4) {
				str += "</tr><tr>";//줄바꿈 (5개단위로 보여주기)
			}

		});
		str += "</tr>";
		str += "</table>"
		$('#result').html(str);
	}//-----------------

	function showPage(total, display, key) {
		
		if (total > 100) {
			total = 100;

		}
		//pageCount=(total-1)/pageSize+1
		var pageCount = Math.floor((total - 1) / display + 1);
		console.log("total=" + total + ", display=" + display);
		//alert(pageCount);
		/* cpage  display   start 
			[1]		20개		1
			[2]   			21
			[3]   			41
			
			start= (cpage-1)*display+1
		 */
		var str = "<ul class='pagination'>";
		for (var i = 1; i <= pageCount; i++) {
			var start = (i - 1) * display + 1;
			str += "<li id='a"+start+"'><a onclick='show(" + start + ",\""
					+ key + "\")'>";
			str += i;
			str += "</a></li>";
		}
		str += "</ul>";

		$('#paging').html("<h3>검색결과:" + total + "개</h3>" + str);

	}//-----
	function show(start, key) {
		//alert(start+", "+key);

		send(key, start);
	}
</script>


	<div class="container">
		<h1 class="text-center">상품검색</h1>
		<div class="col-md-1 col-md-offset-1">
			<label>상품검색</label>
		</div>
		<div class="col-md-8">
			<input type="text" name="search" id="search" class="form-control">
		</div>
		<div>
			<button id="openBtn" class="btn btn-primary">검색</button>
		</div>

		<div id="naverShop" style="margin-top: 50px">
			<div id="result" style="padding: 10px"></div>
			<div id="paging"></div>
		</div>
	</div>

<c:import url="/foot.jsp"/>