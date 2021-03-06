<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="Author" content="구송이">
  <meta name="Keywords" content="문화재청 덕수궁, 반응형 홈페이지">
  <meta name="Description" content="응용sw개발자를 위한 반응형 홈페이지">
  <title>멜로디 디자인</title>
  <script src="/js/jquery-3.3.1.min.js"></script>
  <link href="/css/font-awesome.min.css" rel="stylesheet">
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
					<li class="first"><a href="index.html">HOME</a></li>
					<li><a href="#">카카오톡</a></li>
					<li><a href="#">인스타그램</a></li>
					<li><a href="#">네이버톡톡</a></li>
				</ul>
			</nav>

			<nav class="top_right">
				<ul>
					<c:if test="${session_id eq null}">
					<li class="first">
						<a href="/Member">LOGIN</a>
					</li>
					</c:if>
					<c:if test="${session_id ne null}">
					<li class="first">
						${session_name}님
					</li>	
					<li><a href="/MemberLogout">LOGOUT</a></li>
					</c:if>
					<li><a href="#">JOIN</a></li>
					<li><a href="#">CART</a></li>
					<li><a href="#">ORDER</a></li>
				</ul>
			</nav>

			<div class="gnb_group">
				<h1 class="logo"><span style="color:gray">JSL인재개발원</span></h1>
				<nav class="gnb">
					<ul class="nav_1depth">
						<li><a href="company01.html">COMPANY</a>
							<ul class="nav_2depth">
								<li><a href="company01.html">인사말</a></li>
								<li><a href="company02.html">회사연혁</a></li>
								<li><a href="company03.html">조직도</a></li>
								<li><a href="company04.html">오시는길</a></li>
							</ul>
						</li>
						<li><a href="business01.html">BUSINESS</a>
							<ul class="nav_2depth">
								<li><a href="business01.html">사업영역</a></li>
								<li><a href="business02.html">경영 마인드</a></li>
							</ul>
						</li>
						<li><a href="product01.html">PRODUCT</a>
							<ul class="nav_2depth">
								<li><a href="product01.html">갤러리 게시판</a></li>
								<li><a href="product02.html">유튜브</a></li>
							</ul>
						</li>
						<li><a href="board01.html">BOARD</a>
							<ul class="nav_2depth">
								<li><a href="">공지사항</a></li>
								<li><a href="/News">NEWS</a></li>
								<li><a href="">Q&A</a></li>
								<li><a href="">EVENT</a></li>
								<li><a href="">자주하는질문</a></li>
								<li><a href="">자유게시판</a></li>							
								<li><a href="board01.html">공지사항</a></li>
								<li><a href="board02.html">이용후기</a></li>
								<li><a href="board03.html">1:1 상담</a></li>
								<li><a href="board04.html">FAQ</a></li>
								<li><a href="board05.html">포트폴리오</a></li>
							</ul>
						</li>
						<li><a href="shop01.html">SHOP</a>
							<ul class="nav_2depth">
								<li><a href="shop01.html">카테고리1</a></li>
								<li><a href="shop02.html">카테고리2</a></li>
								<li><a href="shop03.html">카테고리3</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</header>
		<div class="line">
		</div>
	</div>