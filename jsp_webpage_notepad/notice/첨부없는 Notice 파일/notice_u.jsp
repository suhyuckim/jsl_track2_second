﻿<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
<%@ page import="dao.Notice_DAO,dto.Notice_DTO"%>
<%
	Notice_DAO dao 	 = new Notice_DAO();
	String notice_no = request.getParameter("t_noticeNo");
	Notice_DTO dto 	 = dao.getNoticeView(notice_no);
%>
	<div id="menu">
		<ul>
		  <li><img src = "../images/arrow.gif"><a href="/notice/notice_r.jsp">&nbsp;NOTICE</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="/news/news_r.jsp">&nbsp;NEWS</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="/freeboard/freeboard_r.jsp">&nbsp;자유게시판</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="/qanda/qanda_r.jsp">&nbsp;Q & A</a></li>
		</ul>
	</div>
<style>
.bord_list { padding-top : 10px; }
.bord_table { width : 100%; }
.bord_list th {
	border-top : 1px solid #848484 ;
	border-bottom : 1px solid #848484 ;
	padding : 10px ;
}
.bord_table td{
	text-align : center ;
	padding : 10px ;
	border-bottom : 1px solid #D8D8D8 ;
}
td.title {
	text-align : left ;
}
.home_icon{
	width : 15px ;
}
#content .title a {
	color : #6E6E6E;
}
.select_Box {
	text-align : right;
}
.select_Box select {
	height : 20px;
}
#content p a {
	color : black;
}
textarea {
	width : 650px;
	height : 315px;
	resize : none;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
<script>
	function update(){
		var fm 	  = document.notice;
		if(fm.t_title.value == ""){
			alert("제목 입력!");
			fm.t_title.focus();
			return;
		}
		if(fm.t_content.value == ""){
			alert("내용 입력!");
			fm.t_content.focus();
			return;
		}		
		// fm.action = "notice_update.jsp";
		fm.action = "notice_proc.jsp";
		fm.method = "post";
		fm.submit();
	
		//document는 notice_w 자신을 의미함, notice는 글쓰기에서 등록누를때 어떤 영역을 보낼건지 영역을 잡아주는 것임 form 이름이 notice로 정의
		//method (post,get) action 3가지가 필요함
	}
</script>
		<form name ="notice">
		<input type="hidden" name="t_work_gubun" value="update">
		<input type="hidden" name="t_notice_no" value="<%=notice_no%>">
			<div class="bord_list">
				<table class="bord_table">
					<colgroup>
						<col width="10%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<td>작성자</td>
							<td style="text-align:left;"><%=sessionName%></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>제&nbsp;&nbsp;목</td>
							<td style="text-align:left;"><input name="t_title" value="<%=dto.getTitle()%>" type="text" style="width:650px;"></td>
						</tr>
						<tr>
							<td>내&nbsp;&nbsp;용</td>
							<td><textarea name="t_content"><%=dto.getContent()%></textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			<p style="text-align:center; padding-top:10px">
				<a href="/notice/notice_r.jsp"><input type="button" value="목 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
				<a href="javascript:update()"><input type="button" value="수정 저장" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
			</p>
	</div>
		</form>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>