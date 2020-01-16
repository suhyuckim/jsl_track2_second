<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="common.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/sub-news.css" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="/js/jquery-3.3.1.min.js"></script>
		
	<body>
		<!-- skip navigation -->
		<dl id="access">
			<dt>바로가기 및 건너띄기 링크</dt>
			<dd><a href="#contents">본문바로가기</a></dd>
			<dd><a href="#navigation">주메뉴 바로가기</a></dd>
		</dl>
		<hr>
		
		<div id="big-box">
			<header>
			<div id="header">
				<div class="nav">
					<h2 class="readonly">주메뉴</h2>
						<ul class="nav-menu">
						<li><a href="sub1.html">ABOUT EL WIDE</a></li>
						<li><a href="sub2.html">MUSIC</a></li>
						<li><a href="sub3.html">MEDIA</a></li>
						<li><a href="sub4.html">CULTURE</a></li>
						<li><a href="/NoticeList">NOTICE</a></li>
						</ul>
				<div class="logo">
					<h1 class="el-logo"><a href="/Index"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank"
							><i class="fab fa-youtube"> </i></a></li>
							<c:if test="${session_name ne null}">
								<li><a href="/LogOut">logout</a></li>
							</c:if>	
							<c:if test="${session_name eq null}">
								<!-- <li><a href="/member/member_login.jsp"><i class="fas fa-user"></i></a></li>-->				
								<li><a href="/LoginForm">Login</a></li>						
							</c:if>				
						</ul>
					</div>
					<div class="side-text">
						 <ul>
							<c:if test="${session_name ne null}">
						 		<li>${session_name}님 환영합니다.</li>
						 	</c:if>
							 <c:if test="${session_name eq null}">				
								<li>CONNECT WITH WIDE</li>
							</c:if>
						 </ul>
					</div>
				</div>
				</div>
			</div>
			</header>
		</div>		
		<!--  header end -->		
<style>
.select_Box{
	text-align:right;
	margin-right:30px;
	vertical-align : middle;
	margin-bottom:50px;
}
.select_Box select{
	vertical-align : middle;
	height : 25px;
}
.select_Box input[type="text"]{
	height : 25px;
	vertical-align : middle;
}
.button{
	vertical-align : middle;
	width:50px;
	height:25px;
	background:#76b7c0;
	border:none;
	color:white;
}
</style>	
		<!-- sub page start -->		
			<div class="notice">
				<div class="sub-notice">
				<h2><a href="/NoticeList">NOTICE</a></h2>	
				<h2><a href="/event/event_r.jsp"> EVENT</a></h2>
				<h2><a href="/faq/faq_r.jsp"> FAQ</a></h2>	
				<h2 class="color"><a href="/NewsList"><i class="fas fa-check"></i> NEWS</a></h2>	
			</div>
			
			<!-- sub-news start-->
			<div class="news-box">
				<form name="news">
					<p class="select_Box">
						<input type="hidden" name="r_page">
						<input type="hidden" name="t_newsNo">
						<select name="t_sel">
							<option value="title" <c:if test="${selValue == 'title'}"> out.print("selected");</c:if>>제목</option>
							<option value="content" <c:if test="${selValue == 'content'}"> out.print("selected");</c:if>>내용</option>
						</select>
						<input type="text" value="${txtValue}" name="t_search" size="30" maxlength="30"/>
						<input class="button" type="button" onClick="javascript:formSearch()" value="검색">
					</p>
				</form>
		
				
			<c:set var="forYN" value="true"/>	
				<c:if test="${t_dtos.size() > 0}">
					<c:forEach items="${t_dtos}" var="dtos">	
						<c:if test="${forYN}">
							<c:if test="${v_count == for_count}">
							<ul class="news">
								<li>
									<a href=javascript:NewsView("${dtos.getNews_no()}")>
									<c:if test="${dtos.getFile_name_1() ne null}">
										<span class="img"><img src="/file_room/news/${dtos.getFile_name_1()}" alt="뉴스1"></span>
									</c:if>	
									<c:if test="${dtos.getFile_name_1() eq null}">
										<span class="img"><img src="/images/no_image.png"></span>
									</c:if>	
										<p>${dtos.getTitle()}<br></p>
										<span class="size-up"></span>
									</a>
								</li>
							</ul>
								<c:set var="v_count" value="${v_count+1}"/>
								<c:set var="for_count" value="${for_count+1}"/>
							</c:if>
								<c:if test="${v_count != for_count}">
									<c:set var="v_count" value="${v_count+1}"/>
								</c:if>
								<c:if test="${v_count == a_count}">
									<c:set var="forYN" value="false"/>
								</c:if>
						</c:if>	
					</c:forEach>
				</c:if>	
				

				  <c:if test="${t_dtos.size() == 0}">
					<tr align="center" bgcolor="white" >
						<td colspan="7">등록된 내용이 없습니다.</td>
					</tr>	
				  </c:if>	
			</div>
			
			<div class="page-number">
				<%	
					Integer cp = (Integer)request.getAttribute("current_page");	
					int current_page = cp.intValue();
					Integer tp = (Integer)request.getAttribute("total_page");	
					int total_page = tp.intValue();
				    out.println(CommonUtil.pageList2(current_page, total_page));
				%>
				<a href="/NewsWriteForm" class="btn-write">글쓰기</a>
			</div>
		
		</div>
			
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="/index/index.jsp" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>
<script>
	function formSearch(){ //제목 검색
		var fm = document.news; 		
		fm.action = "/NewsList";
		fm.method = "post";
		fm.submit();
	}
	
	function goPage(pageNum){
		var fm = document.news; 
		fm.r_page.value = pageNum;
		fm.action = "/NewsList";
		fm.method = "post";
		fm.submit();
	}
	
	function NewsView(newsview){
		var fm = document.news;
		fm.t_newsNo.value = newsview;
		fm.action = "/NewsView";
		fm.method = "post";
		fm.submit();
	}
</script>
	</body>
</html>