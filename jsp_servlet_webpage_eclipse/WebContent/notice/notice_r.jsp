<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="common.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
<title>project1</title>
<meta charset="utf-8">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/sub-notice.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
<script src="/js/jquery-3.3.1.min.js"></script>
<body>
	<!-- skip navigation -->
	<dl id="access">
		<dt>바로가기 및 건너띄기 링크</dt>
		<dd>
			<a href="#contents">본문바로가기</a>
		</dd>
		<dd>
			<a href="#navigation">주메뉴 바로가기</a>
		</dd>
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
						<h1 class="el-logo">
							<a href="/index/index.jsp"><img src="/images/elwide-logo.svg"
								width="88" height="88"></a>
						</h1>
					</div>
					<div class="side-bar">
						<div class="side-menu">
							<ul>
								<li><a href="http://www.facebook.com/elmusickorea"
									target="_blank"><i class="fab fa-facebook-f"></i></a></li>
								<li><a href="https://blog.naver.com/elmusicstudio"
									target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
								<li><a
									href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"
									target="_blank"><i class="fab fa-youtube"> </i></a></li>
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

	<style>
.select_Box {
	text-align: right;
	margin-right: 50px;
	vertical-align: middle;
}

.select_Box select {
	vertical-align: middle;
	height: 25px;
}

.select_Box input[type="text"] {
	height: 25px;
	vertical-align: middle;
}

.button {
	vertical-align: middle;
	width: 50px;
	height: 25px;
	background: #76b7c0;
	border: none;
	color: white;
}
</style>
	<!-- sub page start -->
	<div class="notice">
		<div class="sub-notice">
			<h2 class="color">
				<a href="/NoticeList"><i class="fas fa-check"></i> NOTICE</a>
			</h2>
			<h2>
				<a href="/event/event_r.jsp"> EVENT</a>
			</h2>
			<h2>
				<a href="/faq/faq_r.jsp"> FAQ</a>
			</h2>
			<h2>
				<a href="/NewsList"> NEWS</a>
			</h2>
		</div>

		<!-- table start-->
		<form name="notice">
			<p class="select_Box">
				<input type="hidden" name="r_page">
				<input type="hidden" name="t_noticeNo">
				 <select name="t_sel">
					<option value="title"
						<c:if test="${selValue == 'title'}"> out.print("selected");</c:if>>제목</option>
					<option value="content"
						<c:if test="${selValue == 'content'}"> out.print("selected");</c:if>>내용</option>
				</select> 
				<input type="text" value="${txtValue}" name="t_search" size="30" maxlength="30" /> 
				<input class="button" type="button" onClick="javascript:formSearch()" value="검색">
			</p>
		</form>

		<div class="table-box">
			<table class="table">
				<caption>공지사항 - 번호, 제목, 첨부, 작성일, 조회수</caption>
				<colgroup>
					<col width="10%">
					<col width="*">
					<col width="7%">
					<col width="15%">
					<col width="10%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">첨부</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>

					<c:set var="forYN" value="true" />
					<c:if test="${t_dtos.size() > 0}">
						<c:forEach items="${t_dtos}" var="dtos">
							<c:if test="${forYN}">
								<c:if test="${v_count == for_count}">
									<tr>
										<td><a
											href=javascript:NoticeView("${dtos.getNotice_no()}")>${dtos.getNotice_no()}</a></td>
										<td class="title">
											<a href=javascript:NoticeView("${dtos.getNotice_no()}")>${dtos.getTitle()}</a></td>
										<td><c:if test="${dtos.getFile_name_1() ne null}">
												<i class="far fa-save"></i>
											</c:if></td>
										<td>${dtos.getReg_id()}</td>
										<td>${dtos.getReg_date()}</td>
										<td>${dtos.getHit()}</td>
									</tr>
									<c:set var="v_count" value="${v_count+1}" />
									<c:set var="for_count" value="${for_count+1}" />
								</c:if>
								<c:if test="${v_count != for_count}">
									<c:set var="v_count" value="${v_count+1}" />
								</c:if>
								<c:if test="${v_count == a_count}">
									<c:set var="forYN" value="false" />
								</c:if>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${t_dtos.size() == 0}">
						<tr align="center" bgcolor="white">
							<td colspan="7">등록된 내용이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="page-number">
			<%
				//String selValue = (String)request.getAttribute("selValue");
				//String txtValue = (String)request.getAttribute("txtValue");
				//String url = "/NoticeList?t_sel="+selValue+"&t_search="+txtValue;
				//String url = "/NoticeList";
				Integer cp = (Integer) request.getAttribute("current_page");
				int current_page = cp.intValue();
				Integer tp = (Integer) request.getAttribute("total_page");
				int total_page = tp.intValue();
				out.println(CommonUtil.pageList2(current_page, total_page));
			%>
			<c:if test="${session_level == 'manager'}">
				<a href="/NoticeWriteForm" class="btn-write">글쓰기</a>
			</c:if>
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
		function formSearch() { //제목 검색
			var fm = document.notice;
			fm.action = "/NoticeList";
			fm.method = "post";
			fm.submit();
		}

		function goPage(pageNum) {
			var fm = document.notice;
			fm.r_page.value = pageNum;
			fm.action = "/NoticeList";
			fm.method = "post";
			fm.submit();
		}
		
		function NoticeView(noticeview){
			var fm = document.notice;
			fm.t_noticeNo.value = noticeview;
			fm.action = "/NoticeView";
			fm.method = "post";
			fm.submit();
		}
	</script>
</body>
</html>