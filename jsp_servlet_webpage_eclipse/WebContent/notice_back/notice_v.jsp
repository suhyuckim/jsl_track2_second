<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="dao.Notice_DAO,dto.Notice_DTO"%>
<%@ include file="/common_session_info.jsp"%>
<%
	Notice_DAO dao 	 = new Notice_DAO();
	String notice_no = request.getParameter("t_noticeNo");
	int nHit 		 = dao.noticeHit(notice_no);
	Notice_DTO dto 	 = dao.getNoticeView(notice_no);
%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/notice-cont.css" rel="stylesheet">
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
						<li><a href="/notice/notice_r.jsp">NOTICE</a></li>
						</ul>
				<div class="logo">
					<h1 class="el-logo"><a href="/member/index.jsp"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank"
							><i class="fab fa-youtube"> </i></a></li>
							<%if(!sessionName.equals("")){%>
							<li><a href="/member/member_logout.jsp"><i class="fas fa-sign-out-alt"></i></a></li>
							<%}else{%>	
							<li><a href="/member/member_login.jsp"><i class="fas fa-user"></i></a></li>							
							<%}%>
					</div>
					<div class="side-text">
						 <ul>
						 <%if(!sessionName.equals("")){%>	
						 	<li>[<%=sessionName%>]님</li>
						 <%}else{%>						
							<li>CONNECT WITH WIDE</li>
						 <%}%>
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
			<h2 class="color"><a href="/notice/notice_r.jsp"><i class="fas fa-check"></i> NOTICE</a></h2>	
			<h2><a href="/event/event_r.jsp"> EVENT</a></h2>
			<h2><a href="/faq/faq_r.jsp"> FAQ</a></h2>	
			<h2><a href="/news/news_r.jsp"> NEWS</a></h2>	
			</div>
<style>
textarea{
	width:100%;
	height:300px;
	border:none;
	resize:none;
}
</style>			
		<!-- cont start-->
		<form name="notice">
		<input type="hidden" name="t_notice_no"  value="<%=notice_no%>">
		<div class="cont-box">
			<h3><%=dto.getTitle()%><br>
				<span><%=dto.getReg_date()%> / <%=dto.getReg_id()%> / 공지사항 </span></h3>
			
			<div>
				<textarea readonly><%=dto.getContent()%></textarea>
			</div>
			
			<p>[첨부파일]</p><br>
			<%if(dto.getFile_name_1() != null){%>		
				<div>
					<td><a href="/common/filedown.jsp?t_file=<%=dto.getFile_name_1()%>&t_gubun=notice"><%=dto.getFile_name_1().substring(8)%></a>
					</td>
				</div>
			<%}%>
		</div>
			
		<!-- sub button start-->
		
		<div class="list">			
			<a href="/notice/notice_r.jsp">View list</a>&nbsp;&nbsp;
			<%if(sessionId.equals("manager")){%>
			<a href="notice_u.jsp?t_noticeNo=<%=dto.getNotice_no()%>">Modify</a>&nbsp;&nbsp;
			<a href="javascript:deleteNotice()">Delete</a>
			<%}%>
		</div>
		</form>		
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
			function deleteNotice(){
				var yn = confirm("정말 삭제 하겠습니까?");
				if(yn==true){
					var fm = document.notice;
					fm.action = "notice_delete.jsp";
					fm.method = "post";
					fm.submit();
				}
			}
		</script>
	</body>	
</html>