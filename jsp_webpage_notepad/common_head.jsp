<!doctype html>
<%@ include file="/common_session_info.jsp" %>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>JSL 방문을 환영합니다</title>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/base.css" />
<link rel="stylesheet" type="text/css" href="/css/etc.css?ver=2" />
<script type = "text/javascript" src="/js/jquery-1.8.1.min.js"></script>
<script type = "text/javascript" src="/js/jquery.cycle2.js"></script>
<script type = "text/javascript" src="/js/common.js"></script> 
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
		  $(".menu6").mouseleave(function(){
			$("#s_div_6").stop().slideUp("fast");
		  });
		   $(".menu6").mouseover(function(){
			$("#s_div_6").stop().slideDown("fast");
		  });
		});
    //]]>
</script>
<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap');

*{margin:0;padding:0;}
li{list-style-type:none;}
a{text-decoration:none;}
a,h1,h2,h3,h4,h5,h6,p,th,td,select,input{
	font-family: 'Gothic A1', sans-serif;
	font-size : 12px ;
}
body { font : 12px "굴림", Gulim ; background : url("/images/background.jpg"); }
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
	width : 110px;
	height : 460px;
	padding : 20px;
	margin-bottom : 20px;
	float : left;
	border : 0px solid #bcbcbc;
//	background : #E0F8F7
}
#content {
	width : 748px;
	height : 100%;
	padding : 20px;
	margin-bottom : 20px;
	float : right;
	border : 0px solid #bcbcbc;
//	background : #A9E2F3;
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
	<%if(sessionLevel.equals("manager")){%>	
		width : 156.66px;
	<% }else{ %>
		width : 188px;
	<% } %>
	height : 20px;
//	background : #00ACD3;
	text-align : center;
	line-height : 20px;
	color : white;
//	opacity : 0.6;
//  background-color : rgb(38,0,155,0.1);
	background: linear-gradient(-45deg, rgba(246, 255, 0, .8), rgba(255, 0, 161, .8)) fixed;
	background-size: cover;
}
#header{
	position : relative;
	background : url("../images/background2.jpg");
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
#s_div_5, #s_div_6{
	display : none;
	position : absolute;
	right : 0px;
}
#s_div_1 ul li a, #s_div_2 ul li a, #s_div_3 ul li a, #s_div_4 ul li a, #s_div_5 ul li a, #s_div_6 ul li a{
	width : 100px;
	background: linear-gradient(-45deg, rgba(246, 255, 0, .8), rgba(255, 0, 161, .8)) fixed;
	background-size: cover;
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

</style>
</head>
<body>
    <div id="container">
		<ul class = "top_right">
			<li><a href="/member/member_w.jsp">JOIN</a></li>
			<%if(!sessionName.equals("")){%>
			<li><a href="/member/member_myinfo.jsp">MYINFO</a></li>	
			<li><a href="/member/member_logout.jsp">LOGOUT</a></li>	
			<%} else {%>
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
			<%if(sessionLevel.equals("manager")){%>			
			<li class="menu6"><a href="/mana/mmember_r.jsp">MANAGER</a>
				<div id="s_div_6">
					<ul>
						<li><a href="/mana/mmember_r.jsp">회원목록</a></li>
						<li><a href="/mana/mmember_r.jsp">직원목록</a></li>					
					</ul>
				</div>
			</li>
			<% } %>
		</ul>
	</div>