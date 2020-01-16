<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="Author" content="구송이">
  <meta name="Keywords" content="문화재청 덕수궁, 반응형 홈페이지">
  <meta name="Description" content="응용sw개발자를 위한 반응형 홈페이지">
  <title>간바테 일본어</title>
  <script src="/js/jquery-3.3.1.min.js"></script>
  <script src="/js/common.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css"> <!-- i태그로 fontawesome 넣어주기 -->
  <link href="css/font-awesome.min.css" rel="stylesheet"> <!-- css으로 fontawesome 넣어주기 -->
	<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
  <link href="/css/common.css" rel="stylesheet">
  <link href="/css/layout.css" rel="stylesheet">	
 
 	<!-- jquery언어 사용법
	1. jquery.js 기반으로 프로그램을 작성하기 때문에 jquery.js 파일을 다운 또는 CDN 방식으로 링크 건다
	2. $(function() {
	실행문;
	});
	-->
</head>

<script>
		$(function(){
			$(".gnb>.nav_1depth>li").hover(function(){
				//console.log("gnb>.nav_1depth>li");
				//$(".main_center .box3 nav ul li").removeClass("active");
				//$(this).addClass("active");
				//$(".clearfix").hide();

				//var activeTab = $(this).find("a").attr("href"); 
                //$(activeTab).fadeIn(); 
                //return false;

				$(this).children(".nav_2depth").stop().slideDown("fast");
				}, function(){
					$(".gnb>.nav_1depth>li").removeClass();
					$(this).children(".nav_2depth").stop().slideUp("fast");
				}
			);
				//$(".main_center .box3 nav ul li").click(function(e){
					//$(this).addClass("active")
					//var num = $(this).index();
					//$(".taball").hide().eq(num).show();
				//}
		});
	</script>

 <body>
 <!-- 웹문서 만들기 기본 공식
 1. 요소를 어떻게 묶을 것인가? 그룹만들기
 2. 그룹안에 적절한 태그 사용
 3. class 이름 붙이고 css 적용
 -->
	<div class="sr-only">
		<p><a href="#contents">본문 바로가기</a></p>
	</div>
	
	<div class="top_navigation">
		<header class="header">
			<nav class="top_left">
				<ul>
					<li class="first"><a href="/Index">HOME</a></li>
					<li><a href="#">카카오톡</a></li>
					<li><a href="#">인스타그램</a></li>
					<li><a href="#">네이버톡톡</a></li>
				</ul>
			</nav>

			<nav class="top_right">
				<ul>
					<c:if test="${session_name ne null}">
						 <li>${session_name}님</li>
					</c:if>
					<c:if test="${session_name ne null}">
						<li class="first"><a href="/MemberLogout">LOGOUT</a></li>
						<li class="first"><a href="/MemberMyinfo">마이페이지</a></li>
					</c:if>
					<c:if test="${session_name eq null}">
						<li class="first"><a href="/MemberLoginForm">LOGIN</a></li>
					</c:if>
					<li><a href="/MemberJoinForm">JOIN</a></li>
					<li><a href="#">CART</a></li>
					<li><a href="#">ORDER</a></li>
				</ul>
			</nav>

			<div class="gnb_group">
				<a href="/Index"><h1 class="logo">간바테일본어</a></h1>
				<nav class="gnb">
					<ul class="nav_1depth">
						<li><a href="company01.html">학원소개</a>
							<ul class="nav_2depth">
								<li><a href="company01.html">인사말</a></li>
								<li><a href="company02.html">회사연혁</a></li>
								<li><a href="company03.html">조직도</a></li>
								<li><a href="company04.html">오시는길</a></li>
							</ul>
						</li>
						<li><a href="business01.html">교육과정</a>
							<ul class="nav_2depth">
								<li><a href="">일본어 기초과정</a></li>
								<li><a href="">일본어 심화과정</a></li>
								<li><a href="">일본어 회화과정</a></li>
								<li><a href="">일본어 시험대비과정</a></li>
							</ul>
						</li>
						<li><a href="product01.html">수강신청</a>
							<ul class="nav_2depth">
								<li><a href="product01.html">JLPT</a></li>
								<li><a href="product02.html">JPT</a></li>
							</ul>
						</li>
						<li><a href="board01.html">커뮤니티</a>
							<ul class="nav_2depth">
								<li><a href="/NoticeList">NOTICE</a></li>
								<li><a href="/NewList_Servlet">NEWS</a></li>
								<li><a href="">FREEBOARD</a></li>								
								<li><a href="">Q&A</a></li>
								<li><a href="/FaqList_Servlet">FAQ</a></li>
								<li><a href="/EventList_Servlet">EVENT</a></li>							
							</ul>
						</li>
						<c:if test="${session_id eq 'manager'}">
							<li><a href="">회원관리</a>
								<ul class="nav_2depth">
									<li><a href="/MemberList_Servlet">회원목록</a></li>
									<li><a href="">직원관리</a></li>
								</ul>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>
		</header>
		<div class="line">
		</div>
	</div>