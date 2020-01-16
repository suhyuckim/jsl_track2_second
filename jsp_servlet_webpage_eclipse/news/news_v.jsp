<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/sub-news-1.css?ver=2" rel="stylesheet">
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
					<h1 class="el-logo"><a href="/index/index.jsp"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
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
								<!-- <li><a href="/member/member_login.jsp"><i class="fas fa-user"></i></a></li>		 -->				
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
		
		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><a href="/NoticeList"> NOTICE</a></h2>	
			<h2><a href="/event/event_r.jsp"> EVENT</a></h2>
			<h2><a href="/faq/faq_r.jsp"> FAQ</a></h2>	
			<h2 class="color"><a href="/NewsList"><i class="fas fa-check"></i> NEWS</a></h2>	
			</div>
			
			<!-- sub-news start-->
			<form name="news">
			<input type="hidden" name="t_news_no">
			<div class="cont">
				<h3>${t_dto.getTitle()}<br>
				<span>${t_dto.getReg_date()} / ${t_dto.getReg_id()}  / NEWS </span><br>
				<span> 조회수 : ${t_dto.getHit()}</span>
				</h3>
				<c:if test="${t_dto.getFile_name_1() ne null}">	
					<img src="/file_room/news/${t_dto.getFile_name_1()}" alt="뉴스1" class="imgS">
				</c:if>
				<c:if test="${t_dto.getFile_name_1() eq null}">	
					<span class="img"><img src="/images/no_image.png"></span>
				</c:if>
				<p class="txt">${t_dto.getContent()}</p>
			</div>
			
			<!-- sub button start-->
			<div class="list">				
				<a href="/NewsList">View list</a>&nbsp;&nbsp;
				<a href=javascript:NewsUpdate("${t_dto.getNews_no()}")>Modify</a>&nbsp;&nbsp;
				<a href="javascript:deleteNews()">Delete</a>
			</div>
			</form>
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
			function deleteNews(){
				var yn = confirm("정말 삭제 하겠습니까?");
				if(yn==true){
					var fm = document.news;
					fm.action = "/NewsDelete";
					fm.method = "post";
					fm.submit();
				}
			}
			
			function NewsUpdate(newsupdate){
				var fm = document.news;
				fm.t_news_no.value = newsupdate;
				fm.action = "/NewsUpdateForm";
				fm.method = "post";
				fm.submit();
			}
		</script>
		
	</body>
</html>