<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
	<title>홍길동</title>
		<meta charset="utf-8">
 		<link href="<%=request.getContextPath()%>/css/noticewrite.css" rel="stylesheet"> 
		<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/join.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/sub-notice.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/sub-qna.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/sub-faq.css" rel="stylesheet">
		
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript">
			function goMyInfo(){
				goForm.action = "/User";
				goForm.method = "post";
				goForm.submit();
			}
		</script>	
		
	<body>
		<!-- skip navigation -->
		<form name="goForm">
			<input type="hidden" name="t_gubun" value="view">
		</form>
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
						<ul class="nav-menu">
							<li><a href="sub1.html">ABOUT EL WIDE</a></li>
							<li><a href="sub2.html">MUSIC</a></li>
							<li><a href="sub3.html">MEDIA</a></li>
							<li><a href="/Notice">NOTICE</a></li>
						</ul>
				<div class="logo">
					<h1 class="el-logo"><a href="/"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank"><i class="fab fa-youtube"> </i></a></li>
							<c:if test="${session_name eq null}">
								<li><a href="/User" title="login"><i class="fas fa-user"></i></a></li>
							</c:if>
							<c:if test="${session_name ne null}">
								<li><a href="javascript:goMyInfo()"><span style="color:white">my</span></a></li>
								<li><a href="/User?t_gubun=userLogout" title="logout"><span style="color:gray"><i class="fas fa-user"></i></span></a></li>
							</c:if>
						</ul>
					</div>
					<div class="side-text">
						 <ul>
						 	<li>
							 	<c:if test="${session_name eq null}">
								 	CONNECT WITH JSL 인재개발원
								 </c:if>
								 <c:if test="${session_name ne null}">
								 	${session_name}님 환영합니다
								 </c:if>
							</li>
						 </ul>
					</div>
				</div>
				</div>
			</div>
			</header>
		</div>