<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common_session_info.jsp"%>
<%@ page import ="common.CommonUtil"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
<%
	String reg_date = CommonUtil.getToday();
%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/noticewrite.css" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="/js/jquery-3.3.1.min.js"></script>
		<script src="/js/common.js"></script>
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
						</ul>
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
			<h2><span><i class="fas fa-edit"></i> EVENT-write</span></h2>	
			</div>
			
			<div class="notice-write">
			
			<form name="write">
					<h2 class="readonly">제목, 첨부파일, 내용을 작성합니다</h2>
				
					<fieldset>
						<legend>공지사항 글쓰기</legend>
						
						<table class="table">
							<caption>공지사항 글쓰기</caption>
							<colgroup>
								<col width="20%">
								<col width="*">
							</colgroup>
							
							<tr>
								<th><label for="title">작성자</label></th>
								<td style="padding-top:10px; padding-bottom:10px" colspan="4"><%=sessionName%></td>
							</tr>
							
							<tr>
								<th><label for="title">제목</label></th>
								<td colspan="4"><input type="text" name="t_title" id="title" class="title" placeholder="제목을 입력해주세요"></td>
							</tr>
							
							<tr>
								<th><label for="cont">내용</label></th>
								<td colspan="4"><textarea type="cont" name="t_cont" id="cont" class="cont" placeholder="내용을 입력해주세요"></textarea>
							</tr>
							
							<tr>
								<input type="hidden" name="t_date">
								<th><label>이벤트기간 </label></th>
								<th><label>시작일 </label></th>
								<td><input type="date" name="reg_start" id="title" class="title" placeholder="ex)20191010"></td>
								<th><label>종료일 </label></th>
								<td><input type="date" name="reg_end" id="title" class="title" placeholder="ex)20191010"></td>
							</tr>
							
							<tr>
								<td colspan="6">
								<a href="javascript:eventcheck('<%=reg_date%>')"><input type="button" value="전송" class="btn" ></a>
								<a href="/event/event_r.jsp"><input type="button" value="목록" class="btn"></a>
								</td>
							</tr>

							</table>
					</fieldset>
				</form>
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
			function eventcheck(reg_d) {
				if(write.t_title.value==""){
					alert("제목을 입력하세요");
					write.t_title.focus();
					return;
				}
				if(write.t_cont.value==""){
					alert("내용을 입력하세요");
					write.t_cont.focus();
					return;
				}
				if(write.reg_start.value==""){
					alert("시직일을 입력하세요");
					write.reg_start.focus();
					return;
				}
				if(write.reg_end.value==""){
					alert("종료일을 입력하세요");
					write.reg_end.focus();
					return;
				}
				
				write.t_date.value = reg_d;
				var s_date = js_removeChar(write.reg_start.value, '-');
				var e_date = js_removeChar(write.reg_end.value, '-');
				
				if(reg_d > s_date){
					alert("시직일자는 오늘 이후로 설정해야 합니다.");
					write.reg_start.focus();
					return;
				}
				if(s_date > e_date){
					alert("종료일자는 시작일 이후로 설정해야 합니다.");
					write.reg_end.focus();
					return;					
				}
				
				write.action = "event_insert.jsp";
				write.method = "post";
				write.submit();
			}
		</script>
	
	</body>
</html>