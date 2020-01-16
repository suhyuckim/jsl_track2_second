<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_session_info.jsp" %>
<%@ page import="java.util.*,dao.Notice_DAO,dto.Notice_DTO,common.CommonUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao = new Notice_DAO();
	String selValue = "title";
	String txtValue = "";
	ArrayList<Notice_DTO> dtos = dao.getNoticeList(selValue,txtValue);

%>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>JSL 방문을 환영합니다</title>
<script type = "text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script type = "text/javascript">
    //<![CDATA[
		$(function(){
		  $(".menu1").mouseover(function(){
			$("#s_div_1").stop().slideDown("fast");
		  });
		  $(".menu1").mouseleave(function(){
			$("#s_div_1").stop().slideUp("fast");
		  });
		  $(".menu2").mouseover(function(){
			$("#s_div_2").stop().slideDown("fast");
		  });
		  $(".menu2").mouseleave(function(){
			$("#s_div_2").stop().slideUp("fast");
		  });
		  $(".menu3").mouseover(function(){
			$("#s_div_3").stop().slideDown("fast");
		  });
		  $(".menu3").mouseleave(function(){
			$("#s_div_3").stop().slideUp("fast");
		  });
		  $(".menu4").mouseover(function(){
			$("#s_div_4").stop().slideDown("fast");
		  });
		  $(".menu4").mouseleave(function(){
			$("#s_div_4").stop().slideUp("fast");
		  });
		  $(".menu5").mouseover(function(){
			$("#s_div_5").stop().slideDown("fast");
		  });
		  $(".menu5").mouseleave(function(){
			$("#s_div_5").stop().slideUp("fast");
		  });
		});
    //]]>
</script>
<style>
@import url('https://fonts.googleapis.com/css?family=Gugi&display=swap');
*{margin:0;padding:0;}
li{list-style-type:none;}
a{text-decoration:none;}
a,h1,h2,h3,h4,h5,h6,p,th,td{
	font-family: 'Gugi', cursive;
	font-size : 12px ;
}
body { font : 12px "굴림", Gulim ; background : url("images/mini.jpg") ; }
#container {
	width : 940px;
	margin : 0px auto;
//	margin : 0px;
	padding : 20px;
	border : 0px solid #bcbcbc;
	background : white;
}
#header {
	width : 940px;
	height : 140px;
	padding : 0px;
	margin-bottom : 20px;
	border : 0px solid red;
	background : #F5DA81;
}
#menu {
	width : 900px;
	height : 460px;
	padding : 20px;
	margin-bottom : 20px;
	float : left;
	border : 0px solid #bcbcbc;
	background : url("images/minions.jpg");
	<!-- background : rgb(50,50,100,0.1); -->
}
#footer {
	clear : both;
	padding : 20px;
	border : 1px solid #bcbcbc;
	color : white;
}
.header_menu li{
	float : left;
}
.header_menu li a{
	display : inline-block;
	width : 188px;
	height : 20px;
//	background : #00ACD3;
	text-align : center;
	line-height : 20px;
	color : white;
//	opacity : 0.8;
	background-color : rgb(4,0,17,0.3);
}
#header{
	position : relative;
	background : url("images/mini2.jpg");
	background-size : 100% 100%;
}
.header_menu{
	position : absolute;
	top : 120px;
}
#s_div_1, #s_div_2, #s_div_3, #s_div_4{
	position : absolute;
	display : none;
}
#s_div_5{
	display : none;
	position : absolute;
	right : 0px;
}
#s_div_1 ul li a, #s_div_2 ul li a, #s_div_3 ul li a, #s_div_4 ul li a, #s_div_5 ul li a{
	width : 100px;
	background : #787878;
}
.top_right{
	position : relative;
	top : -15px;
}
.top_right li {
	float : right;
	border-right : 1px solid black;
}
.top_right li a{
	display : inline-block;
	width : 50px;
	text-align : center;
	color :  black;
	font-size : 10px;
}
#menu li a{
	font-size : 14px;
	color : black;
	line-height : 30px;
	font-weight : bold ;
}
#menu li {
	border-bottom : 1px dotted black ;
}
</style>
</head>
<body>
    <div id="container">
		<ul class = "top_right">
			<li><a href="/member/member_w.jsp">JOIN</a></li>
<%if(!sessionName.equals("")){%>			
			<li><a href="/member/member_myinfo.jsp">MYInfo</a></li>	
			<li><a href="/member/member_logout.jsp">LOGOUT</a></li>	
<%}else{%>			
			<li><a href="/member/member_login.jsp">LOGIN</a></li>
<%}%>				
			<li><a href="../index.jsp"> 
<%if(!sessionName.equals("")){%>				
				[<%=sessionName%>]님				
<%}%>	
				HOME</a>
			</li>
		</ul>
      <div id="header">
        <ul class="header_menu">
			<li class="menu1"><a href="#">회사안내</a>
			
				<div id="s_div_1">
					<ul>
						<li><a href="">CEO 인사말</a></li>
						<li><a href="">전년판매현황</a></li>
						<li><a href="">VISION</a></li>
						<li><a href="">찾아오는길</a></li>
					</ul>
				</div>
			</li>
			<li class="menu2"><a href="#">제품안내</a>
				<div id="s_div_2">
					<ul>
						<li><a href="">DESKTOP</a></li>
						<li><a href="">NOTEBOOK</a></li>
						<li><a href="">PRINTER</a></li>
						<li><a href="">BEAM</a></li>
						<li><a href="">주변기기</a></li>
					</ul>
				</div>
			</li>
			<li class="menu3"><a href="#">판매안내</a>
				<div id="s_div_3">
					<ul>
						<li><a href="">Online</a></li>
						<li><a href="">Offline</a></li>
					</ul>
				</div>
			</li>
			<li class="menu4"><a href="#">주문안내</a>
				<div id="s_div_4">
					<ul>
						<li><a href="">배송안내</a></li>
						<li><a href="">환불안내</a></li>
					</ul>
				</div>
			</li>
			<li class="menu5"><a href="/notice/notice_r.jsp">커뮤니티</a>
				<div id="s_div_5">
					<ul>
						<li><a href="/notice/notice_r.jsp">NOTICE</a></li>
						<li><a href="/news/news_r.jsp">NEWS</a></li>
						<li><a href="/freeboard/freeboard_r.jsp">자유게시판</a></li>
						<li><a href="/qanda/qanda_r.jsp">Q & A</a></li>
					</ul>
				</div>
			</li>
		</ul>
      </div>
<style>
.main_menu{
	background:#A9E2F3;
	width:900px;
	height:600px;
	padding:20px;
	margin-bottom:20px;
	<--메인--> 
}
.menu11{
	background:#F79F81;
	width:350px;
	height:600px;
	float:right;
	<--오른쪽이미지 --> 
}
.t_notice{
	background:#A9F5D0;
	width:500px;
	height:200px;
	float:left;
	margin-bottom:20px;
	<--위 공지사항 --> 
}
.t_news{
	background:#F5A9F2;
	width:500px;
	height:200px;
	float:left;
	margin-bottom:20px;
	<--아래 뉴스 --> 
}
.image_1{
	background:#A9F5F2;
	width:152px;
	height:155px;
	float:left;
	margin-right:20px;
	<--이미지_1 --> 
}
.image_2{
	background:#FA5858;
	width:152px;
	height:155px;
	float:left;
	margin-right:20px;
	<--이미지_2 --> 
}
.image_3{
	background:#2E64FE;
	width:152px;
	height:155px;
	float:left;
	<--이미지_3 --> 
}
</style>
      <div class="main_menu">
		<div class="menu11">
			오른쪽이미지
		</div>
		<div class="t_notice">
			<p>공지사항</p>
		</div>
		<div class="t_news">
			뉴스
		</div>
		<div class="image_1">
			이미지_1
		</div>
		<div class="image_2">
			이미지_2
		</div>
		<div class="image_3">
			이미지_3
		</div>
	  </div>
	  
<style>
#footer{
	background : #42464d ;
	padding-top : 10px ;
	padding-bottom : 10px ;
}
#footer .address {
	color : #ababb1;
	margin-bottom : 10px;
}
#footer .address .title{
	font-size : 14px ;
	margin-bottom : 8px;
	color : #fff;
}
#footer .copyright {
	color : #fff ;
}
</style>
	<div id="footer">
		<address class="address">
			<p class="title">본사</p><em>
			(우)12345 대전광역시 중구 계룡로 825
			(용두동, 희영빌딩) 5층, 6층/고객센터 : 042-242-4412
			<br>사업자등록번호 : 305-86-06709</em>
		</address>
        <p class="copyright">COPYRIGHT &copy; JSL 전자개발주식회사. All rights reserved</p>
      </div>
    </div>
  </body>
</html>