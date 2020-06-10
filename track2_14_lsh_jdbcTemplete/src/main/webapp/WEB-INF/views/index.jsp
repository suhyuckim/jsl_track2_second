<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
					<li class="first"><a href="/">HOME</a></li>
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

	<section id="main_visual">
		<div class="visual_wrap">
			<h2 class="sr-only">메인 비쥬얼</h2>
			<ul>
				<!-- <li><img src="images/bg.png" alt="학생단체관람" class="w100"></li> -->
				<li class="one"></li>
			</ul>
			<div class="visual_inner">
				<p class="txt">Challenging Technology Development, <br/> Leading-Edge Enterprise</p>
			</div>
		</div>
	</section>

	<section class="news_group">
		<div class="news_tit">
			<p class="sub_tit">OUR WORK</p>
			<h2>기업홍보 <br/>쇼핑몰빌더</h2>
			<p class="text">글로벌 경쟁력을 기반으로 최고의 IT 서비스 가치를 업그레이드하며 한단계 앞서는 새로운 패러다임을 이끌어가는 기업이 되겠습니다.</p>
			<a href="" class="btn-border">READ MORE</a>
		</div>
		<ul class="news_list">
			<li>
				<a href="">
					<img src="images/news-01.jpg" alt="COMPNAY" class="w100">
					<strong>COMPANY</strong>
					<p>전문화된 사회적 기업. 개성과 창조적인 기술을 지원합니다.</p>
					<span>2018-09-20</span>
					<div class="over">
						<strong>COMPANY</strong>
						<p>전문화된 사회적 기업. 개성과 창조적인 기술을 지원합니다.</p>
						<span>2018-09-20</span>
					</div>
				</a>
			</li>
			<li>
				<a href="">
					<img src="images/news-02.jpg" alt="BUSINESS" class="w100">
					<strong>BUSINESS</strong>
					<p>믿음과 신뢰의 디자인 서비스. 고객님의 만족을 높여드립니다.</p>
					<span>2018-09-20</span>
					<div class="over">
						<strong>BUSINESS</strong>
						<p>믿음과 신뢰의 디자인 서비스. 고객님의 만족을 높여드립니다.</p>
						<span>2018-09-20</span>
					</div>
				</a>
			</li>
			<li class="news_end">
				<a href="">
					<img src="images/news-03.jpg" alt="CONTACT" class="w100">
					<strong>CONTACT</strong>
					<p>제휴 및 견적의뢰를 보내어주시면 빠른 답변드리겠습니다.</p>
					<span>2018-09-20</span>
					<div class="over">
						<strong>CONTACT</strong>
						<p>제휴 및 견적의뢰를 보내어주시면 빠른 답변드리겠습니다.</p>
						<span>2018-09-20</span>
					</div>
				</a>
			</li>
		</ul>
	</section>
	<section class="main_content">
		<h2 class="sr-only">주요서비스<h2>
		<div class="main_left">
			<div class="box1">
				<div class="txt_relative">
					<h3>ONLINE SHOP</h3>
					<p>메인 상품은 관리자 페이지에서 선택 가능합니다.</p>
					<a href="" class="btn-border">READ MORE</a>
				</div>
				<div class="img_transition">
				</div>
			</div>
			<div class="box2">
			</div>
		</div>
		<div class="main_center">
			<div class="box3">
				<h3 class="sr-only">알림마당</h3>
				<nav>
					<ul>
						<c:if test="${t_news.size() > 0}">
							<c:forEach items="${t_news}" var="news" begin="0" end="0">
								<li class="active"><a href="#sam01">${news.getTitle()}</a></li>
								<li><a href="#sam02"></a></li>
							</c:forEach>
						</c:if>
					</ul>
				</nav>
				<br>
				<div class="sample01 clearfix active" id="sam01">
					<div class="sam">
						<c:if test="${t_news.size() > 0}">
							<c:forEach items="${t_news}" var="news" begin="0" end="0">					
								<p class="title">
									<span class="date">${news.getReg_date()}</span>
								</p>
									<p class="text">${news.getContent()}</p>
							</c:forEach>
						</c:if>	
					</div>
					<ul>
						<c:forEach items="${t_news}" var="news" begin="1" end="4">
							<li><a href="">${news.getTitle()}</a><span>${news.getReg_date()}</span></li>
						</c:forEach>
					</ul>
				</div>
			</div> <!-- box3 -->
			<div class="box4">
			</div>
			<div class="box5">
			</div>
		</div> <!-- main_center -->

		<div class="main_right">
		</div>
	</section>

	<section class="other">
		<h2 class="sr-only">기타서비스</h2>
		<div class="container clearfix">

			<div class="other_left">
				<h3 class="sr-only">INSTAGRAM</h3>
				<div class="title_box">
					<em>instagram</em>
					<p class="title">인스타그램 <strong>갤러리 </br>리스트입니다</strong></p>
					<p class="text">FOLLOW @gu_songi</p>
					<a href="" class="btn-border white">READ MORE</a>
				</div>
				<div class="content_box">
				</div>
			</div>

			<div class="other_right">
				<div class="title_box">
					<em>instagram</em>
					<p class="title">인스타그램 <strong>갤러리 </br>리스트입니다</strong></p>
					<p class="text">FOLLOW @gu_songi</p>
					<ul>
						<li>새 게시물이 없습니다.</li>
						<li>새 게시물이 없습니다.</li>
						<li>새 게시물이 없습니다.</li>
						<li>새 게시물이 없습니다.</li>
					</ul>
				</div>
				<div class="content_box">
				</div>
			</div>

		</div>
	</section>
	<script>
		$(function(){
			$(".gnb>.nav_1depth>li").hover(function(){
				//console.log("gnb>.nav_1depth>li");
				$(this).parent().addClass("active");
				$(".taball").hide();
				$($(this).attr("href")).show();
			});
		});
	</script>

	<div class="right_quick">
		<button class="btn btn-open" type="button"></button>
	</div>
<footer class="footer">
	<div class="container clearfix">
		<address class="address">
			<p class="title">본사</p>
			(우)12345 대전광역시 서구 관저로48<br>
			전화번호 042) 123-4567 팩스 042) 123-4567
		</address>
		<p class="copyright">COPYRIGHT &copy.VISION. ALL RIGHTS RESERVED.</p>
	</div>
</footer>
 </body>


</html>
