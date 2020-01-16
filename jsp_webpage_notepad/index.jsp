<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_session_info.jsp" %>
<%@ page import="java.util.*,dao.Notice_DAO,dto.Notice_DTO,common.CommonUtil"%>
<%@ page import="dao.Freeboard_DAO,dto.Freeboard_DTO"%>
<%@ page import="dao.News_DAO,dto.News_DTO"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	Notice_DAO daoN    = new Notice_DAO();
	News_DAO   daoW	   = new News_DAO();
	Freeboard_DAO daoF = new Freeboard_DAO();
	
	ArrayList<Notice_DTO>      dtosN = daoN.getNoticeIndex();
	ArrayList<News_DTO>   	   dtosW = daoW.getNewsIndex();
	ArrayList<Freeboard_DTO>   dtosF = daoF.getFreeboardIndex();
	
	int total_countN;
	int total_countW;
	int total_countF;
	
	if(dtosN == null) total_countN = 0;
	else total_countN = dtosN.size();
	
	if(dtosW == null) total_countW = 0;
	else total_countW = dtosW.size();
	
	if(dtosF == null) total_countF = 0;
	else total_countF = dtosF.size();
%>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>JSL 방문을 환영합니다</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<script type = "text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script type = "text/javascript" src="/js/jquery.cycle2.js"></script>
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
body { font : 12px "굴림", Gulim ; background : url("images/background.jpg") ; }
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
//	background : url("images/background.jpg");
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
	background: linear-gradient(-45deg, rgba(246, 255, 0, .8), rgba(255, 0, 161, .8)) fixed;
	background-size: cover;
}
#header{
	position : relative;
	background : url("images/background2.jpg");
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
	width:900px;
	height:500px;
	padding:20px;
	margin-bottom:20px;
}
.board{
	width : 900px;
	height : 250px;
}
.notice{
	width : 266px;
	height : 230px;
	float:left;
	padding:10px;
	box-shadow : 0 2px 5px #D8D8D8;
	font-family:'고딕';
}
.news{
	width : 266px;
	height : 230px;
	float:left;
	padding:10px;
	margin-left:20px;
	margin-right:20px;
	box-shadow : 0 2px 5px #D8D8D8;
	font-family:'고딕';
}
.plus{
	float:right;
	color : #F5A9A9;
}
.notice_title, .news_title, .freeboard_title{
	font-size:14px;
	color : #F5A9A9;
	font-family:'고딕';	
}
.notice_table td, .news_table td, .freeboard_table td{
	border-bottom : 1px solid #D8D8D8;
	padding:7px;
}
.cycle-slideshow{
	width:900px;
	height:250px;
	margin-bottom:20px;
	background-color : #CEF6EC;
}
.fas{
	color : #F5A9A9;
}
a { text-decoration: none; color: black; }
a:visited { text-decoration: none; }
a:hover { text-decoration: none; }
a:focus { text-decoration: none; }
a:hover, a:active { text-decoration: none; }
</style>
      <div class="main_menu">
		<div class="cycle-slideshow" data-cycle-fx="scrollHorz" data-cycle-pause-on-hover="true"  data-cycle-speed="200">
			<img src="/images/slide/123.JPG">
			<img src="/images/slide/12.JPG">
			<img src="/images/slide/1.JPG">
		</div>
		<div class="board">
			<div class="notice">
				<p class="notice_title"><i class="fas fa-exclamation-circle"></i>&nbsp;NOTICE<p><br>
				<table class="notice_table">
					<colgroup>
						<col width="250px">
						<col width="120px">					
					</colgroup>
				<%if(total_countN > 0){
					for(int k = 0 ; k < total_countN ; k++ ){
                %> 
				<tr>
					<td><a href="/notice/notice_v.jsp?t_noticeNo=<%=dtosN.get(k).getNotice_no()%>"><i class="fas fa-circle" style="font-size:3px">&nbsp;</i><%=dtosN.get(k).getTitle()%>
					<%if(dtosN.get(k).getTitle().length() > 7){%>...<%}%>
					</a></td>
					<td style="text-align:right">[<%=dtosN.get(k).getReg_date()%>]</td>
				</tr>
				<%
                    }            
                }
                %>
				</table><br>
						<p class="plus"><i class="fas fa-plus">&nbsp;</i><a href="/notice/notice_r.jsp">more</a></p>
			</div>
			<div class="news">
				<p class="news_title"><i class="fas fa-file-alt"></i>&nbsp;NEWS</p><br>
				<table class="news_table">
					<colgroup>
						<col width="250px">
						<col width="120px">					
					</colgroup>
				<%if(total_countW > 0){
					for(int k = 0 ; k < total_countW ; k++ ){
                %> 
				<tr>
					<td><a href="/news/news_v.jsp?t_newsNo=<%=dtosW.get(k).getNews_no()%>"><i class="fas fa-circle" style="font-size:3px">&nbsp;</i><%=dtosW.get(k).getTitle()%>
					<%if(dtosW.get(k).getTitle().length() > 7){%>...<%}%>
					</a></td>
					<td style="text-align:right">[<%=dtosW.get(k).getReg_date()%>]</td>
				</tr>
				<%
                    }            
                }
                %>
				</table><br>
					<p class="plus"><i class="fas fa-plus">&nbsp;</i><a href="/news/news_r.jsp">more</a></p>
			</div>
			<div class="freeboard">				
					<div id="daumRoughmapContainer1572847749430" class="root_daum_roughmap root_daum_roughmap_landing"></div>
					<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
<script charset="UTF-8">
new daum.roughmap.Lander({
	"timestamp" : "1572847749430",
	"key" : "vp69",
	"mapWidth" : "265",
	"mapHeight" : "185"
}).render();
</script>		
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