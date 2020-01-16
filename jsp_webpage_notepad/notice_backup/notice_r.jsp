<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Member_DAO"%>
<%
	Member_DAO dao = new Member_DAO();
	String id = dao.getMaxId();
%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>JSL 방믄을 환영합니다</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../css/reset.css" />
<link rel="stylesheet" type="text/css" href="../css/base.css" />
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
//body { font : 12px "굴림", Gulim ; background : white ; }
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
#menu .arrow {
	vertical-align : middle;
}
</style>
</head>
<body>
    <div id="container">
<%="====d===="+id%>
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
		  <li><img class="arrow" src = "../images/arrow.gif"><a href="notice_r.jsp">&nbsp;NOTICE</a></li>
		  <li><img class="arrow" src = "../images/arrow2.gif"><a href="#">&nbsp;NEWS</a></li>
		  <li><img class="arrow" src = "../images/arrow2.gif"><a href="#">&nbsp;자유게시판</a></li>
		  <li><img class="arrow" src = "../images/arrow2.gif"><a href="#">&nbsp;Q & A</a></li>
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
	padding : 8px ;
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
.button {
	width : 50px;
	height : 20px;
	color : #fff;
	background:#42464d;
	border:none;
}
.paging {padding-top:30px; text-align:center;}
.paging a  {padding:10px; border:1px solid #e0e0e0;}
.paging a.active {background:#42464d; color:#fff;}

.paging .btn_write {
	background:#42464d; 
	padding:10px 16px; 
	color:#fff; 
	float:right;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
		<p class="select_Box">
			<select>
				<option value="f?">제목</option>
				<option value="f?">내용</option>
			</select>
			<input type="text" size="30" maxlength="30"/>
			<input class="button" type="button" value="검색">
		</p>
		<div class="bord_list">
			<table class="bord_table">
				<colgroup>
					<col width="10%">
					<col width="*">
					<col width="10%">
					<col width="10%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>10</td>
						<td class="title"><a href="notice_v.jsp">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>125</td>
					</tr>
					<tr>
						<td>9</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>111</td>
					</tr>
					<tr>
						<td>8</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>145</td>
					</tr>
					<tr>
						<td>7</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>137</td>
					</tr>
					<tr>
						<td>6</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>149</td>
					</tr>
					<tr>
						<td>5</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>101</td>
					</tr>
					<tr>
						<td>4</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>91</td>
					</tr>
					<tr>
						<td>3</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>166</td>
					</tr>
					<tr>
						<td>2</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>211</td>
					</tr>
					<tr>
						<td>1</td>
						<td class="title"><a href="">PC 구매절차 방법</a></td>
						<td>관리자</td>
						<td>19-09-17</td>
						<td>301</td>
					</tr>
				</tbody>
			</table>
			<div class="paging">
				<a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="" class="active">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-right"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a>
				<a href="notice_w.jsp" class="btn_write">글쓰기</a> 
			</div>
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