<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="dao.Notice_DAO,dto.Event_DTO,dao.Event_M_DAO,dto.Event_M_DTO"%>
<%@ include file="/common_session_info.jsp"%>
<%
	Notice_DAO dao 	 	= new Notice_DAO();
	Event_M_DAO mdao    = new Event_M_DAO();
	String event_no  	= request.getParameter("t_eventNo");
	String reg_id 		= sessionId;
	int reg_date        = Integer.parseInt(CommonUtil.getToday());
	int membercount		= mdao.EventMemberCount(event_no, reg_id);
	int nHit 		 	= dao.eventHit(event_no);
	Event_DTO dto 	 	= dao.getEventView(event_no);
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
					<h1 class="el-logo"><a href="/index/index.jsp"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
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
			<h2><a href="/notice/notice_r.jsp"> NOTICE</a></h2>	
			<h2 class="color"><a href="/event/event_r.jsp"><i class="fas fa-check"></i> EVENT</a></h2>
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
		<form name="event">
		<input type="hidden" name="t_event_no"  value="<%=event_no%>">
		<div class="cont-box">
			<h3><%=dto.getTitle()%><br>
				<span> 작성일 : <%=dto.getReg_date()%><br> 작성자 : <%=dto.getReg_id()%><br> 이벤트 기간 : <%=dto.getReg_start()%> ~ <%=dto.getReg_end()%></span></h3>
			<div>
				<textarea readonly><%=dto.getContent()%></textarea>
			</div>
		</div>
		
		<!-- sub button start-->		
		<div class="list">		
			<a href="/event/event_r.jsp">View list</a>&nbsp;&nbsp;
			<%if(membercount == 0 && reg_date <= CommonUtil.removeChar(dto.getReg_end())){%>
				<a href="event_a.jsp?t_eventNo=<%=dto.getEvent_no()%>">Apply</a>&nbsp;&nbsp;
			<%}%>	
			<%if(membercount != 0 && reg_date <= CommonUtil.removeChar(dto.getReg_end())){%>
				<a href="javascript:deleteEvent_m()">Cancel</a>&nbsp;&nbsp;
			<%}%>
			<%if(sessionId.equals("manager")){%>
				<a href="event_u.jsp?t_eventNo=<%=dto.getEvent_no()%>">Modify</a>&nbsp;&nbsp;
			<%}%>
			<%if(sessionId.equals("manager") && membercount == 0 && reg_date <= CommonUtil.removeChar(dto.getReg_end())){%>
				<a href="javascript:deleteEvent()">Delete</a>&nbsp;&nbsp;
			<%}%>
			<%if(!sessionId.equals("") && membercount != 0 && reg_date <= CommonUtil.removeChar(dto.getReg_end())){%>
				<a href="event_mu.jsp?t_eventNo=<%=dto.getEvent_no()%>">이벤트 응모 수정</a>
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
			function deleteEvent(){
				var yn = confirm("정말 삭제 하겠습니까?");				
				if(yn==true){					
					var fm = document.event;
					fm.action = "event_delete.jsp";
					fm.method = "post";
					fm.submit();
				}
			}
			function deleteEvent_m(){
				var yn = confirm("정말 응모 취소 하겠습니까?");				
				if(yn==true){					
					var fm = document.event;
					fm.action = "event_m_delete.jsp";
					fm.method = "post";
					fm.submit();
				}
			}
		</script>
	</body>	
</html>