<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="dao.Notice_DAO,dto.Notice_DTO"%>
<%@ include file="/common_session_info.jsp"%>
<%
	Notice_DAO dao 	 = new Notice_DAO();
	String notice_no = request.getParameter("t_noticeNo");
	Notice_DTO dto 	 = dao.getNoticeView(notice_no);
%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/noticewrite.css" rel="stylesheet">
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
			<h2><span><i class="fas fa-edit"></i> NOTICE-update</span></h2>	
			</div>
			
			<div class="notice-write">
			
			<form name="write" enctype="multipart/form-data">
				<input type="hidden" name="t_notice_no"  value="<%=notice_no%>">
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
								<td style="padding-top:10px; padding-bottom:10px";><%=sessionName%></td>
							</tr>
							
							<tr>
								<th><label for="title">제목</label></th>
								<td><input type="text" name="title" id="title" class="title" value="<%=dto.getTitle()%>" placeholder="제목을 입력해주세요"></td>
							</tr>
							
							<tr>
								<th><label for="cont">내용</label></th>
								<td><textarea type="cont" name="cont" id="cont" class="cont" placeholder="내용을 입력해주세요"><%=dto.getContent()%></textarea>
							</tr>
							
							<tr>
								<th><label for="file">파일 첨부</label></th>
								<td>
								<%if(dto.getFile_name_1() != null){%>
									<a href="/common/filedown.jsp?t_file=<%=dto.getFile_name_1()%>&t_gubun=notice"><%=dto.getFile_name_1()%></a>
									&nbsp;&nbsp;파일삭제
									<input type="checkbox" name="checkBox_del_fileName" value="<%=dto.getFile_name_1()%>">
									<br>
								<%}%>	
									<br>
									<input type="file" name="fileName_a">
									<input type="hidden" name="ori_fileName_a" value="<%=dto.getFile_name_1()%>">
								</td>
							</tr>
							
							<tr>
								<td colspan="2">
								<a href="javascript:save()"><input type="button" value="저장" class="btn" ></a>
								<a href="/notice/notice_r.jsp"><input type="button" value="목록" class="btn"></a>
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
			function save() {
				if(write.title.value==""){
				alert("제목을 입력하세요");
				write.title.focus();
				return;
				}
				if(write.cont.value==""){
				alert("내용을 입력하세요");
				write.cont.focus();
				return;
				}		
			
				write.action = "notice_update.jsp";
				write.method = "post";
				write.submit();
			}
		</script>
	
	</body>
</html>