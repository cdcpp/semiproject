<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="search.*"%>
<%
	String keyword=request.getParameter("keyword");
	String start=request.getParameter("start");
	String display=request.getParameter("display");
	
	if(keyword==null){
		keyword="Ajax"; //기본 검색어를 Ajax로 설정함
	}
	if(start==null){
		start="1";
	}
	if(display==null){
		display="10"; //한 페이지 당 20개씩 보여주기 (디폴트로 설정)
	}

	int startInt=Integer.parseInt(start);
	int dispInt=Integer.parseInt(display);
	SearchNaverProxy bnp=new SearchNaverProxy();
	String data=bnp.getData(keyword, startInt, dispInt);
	out.println(data);
%>    

