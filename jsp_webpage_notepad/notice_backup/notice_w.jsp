<!doctype html>
<html lang="ko">
<head>
<title>JSL 방문을 환영합니다</title>
<script type = "text/javascript" src="../js/jquery-1.8.1.min.js"></script>
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
a,h1,h2,h3,h4,h5,h6,p,th,td,select,input{
	font-family: 'Gugi', cursive;
	font-size : 12px ;
}
body { font : 12px "굴림", Gulim ; background : url("../images/bg_grid.gif") ; }
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
	height : 460px;
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
	background : url("../images/back_img_4_1.jpg");
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
			<li><a href="">JOIN</a></li>
			<li><a href="">LOGIN</a></li>
			<li><a href="../index.html">HOME</a></li>
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
			<li class="menu5"><a href="notice_r.jsp">커뮤니티</a>
				<div id="s_div_5">
					<ul>
						<li><a href="notice_r.jsp">NOTICE</a></li>
						<li><a href="">NEWS</a></li>
						<li><a href="">자유게시판</a></li>
						<li><a href="">Q & A</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div id="menu">
		<ul>
		  <li><img src = "../images/arrow.gif"><a href="notice_r.jsp">&nbsp;NOTICE</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="#">&nbsp;NEWS</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="#">&nbsp;자유게시판</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="#">&nbsp;Q & A</a></li>
		</ul>
	</div>
<style>
.bord_list { padding-top : 10px; }
.bord_table { width : 100%; }
.bord_list th {
	border-top : 1px solid #848484 ;
	border-bottom : 1px solid #848484 ;
	padding : 10px ;
}
.bord_table td{
	text-align : center ;
	padding : 10px ;
	border-bottom : 1px solid #D8D8D8 ;
}
td.title {
	text-align : left ;
}
.home_icon{
	width : 15px ;
}
#content .title a {
	color : #6E6E6E;
}
.select_Box {
	text-align : right;
}
.select_Box select {
	height : 20px;
}
#content p a {
	color : black;
}
textarea {
	width : 650px;
	height : 315px;
	resize : none;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
		<div class="bord_list">
			<table class="bord_table">
				<colgroup>
					<col width="10%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<td>작성자</td>
						<td style="text-align:left;">관리자</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>제&nbsp;&nbsp;목</td>
						<td style="text-align:left;"><input type="text" style="width:650px;"></td>
					</tr>
					<tr>
						<td>내&nbsp;&nbsp;용</td>
						<td><textarea></textarea></td>
					</tr>
				</tbody>
			</table>
		</div>
		<p style="text-align:center; padding-top:10px">
			<a href="notice_r.jsp"><input type="button" value="목 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
			<input type="button" value="등 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;">
		</p>
	</div>
<style>
#footer{
	background : #42464d ;
	padding-top : 10px ;
	padding-bottom : 10px ;
}
#footer .address {
	font-style : narmal;
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
			<p class="title">본사</p>
			(우)12345 대전광역시 중구 계룡로 825
			(용두동, 희영빌딩) 5층, 6층/고객센터 : 042-242-4412
			<br>사업자등록번호 : 305-86-06709
		</address>
        <p class="copyright">COPYRIGHT &copy; JSL 전자개발주식회사. All rights reserved</p>
      </div>
    </div>
  </body>
</html>